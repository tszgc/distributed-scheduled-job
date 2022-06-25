package org.nina.executor.task.delay;

import org.apache.commons.lang3.StringUtils;
import org.nina.dsj.common.dto.TaskDto;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 描述：延时任务执行器
 * 作者：zgc
 * 时间：2022/6/25 08:55
 */
@Component
public class DelayTaskExecutor implements ApplicationRunner {

    // 延时队列
    private final DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    private ConcurrentHashMap<String, Future> runningTaskFutureMap = new ConcurrentHashMap<>();

    private volatile boolean run = true;

    // 任务执行线程池
    ThreadPoolExecutor executorPool = new ThreadPoolExecutor(5, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), new CustomizableThreadFactory("delayTask"), new ThreadPoolExecutor.CallerRunsPolicy());

    public void produce(TaskDto taskDto) {
        produce(new DelayTask(taskDto));
    }

    public void produce(DelayTask delayTask) {
        delayQueue.put(delayTask);
    }

    public void start() {
        Executors.newSingleThreadExecutor().submit(()->{
            while(run) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                    DelayTask task = delayQueue.take();
                    Future<?> future = executorPool.submit(task);
                    runningTaskFutureMap.put(task.getTaskDto().getCode(), future);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancel(TaskDto taskDto) {
        cancel(new DelayTask(taskDto));
    }

    public void cancel(DelayTask delayTask) {
        delayQueue.removeIf(task -> StringUtils.equals(task.getTaskDto().getCode(), delayTask.getTaskDto().getCode()));
        if (runningTaskFutureMap.contains(delayTask.getTaskDto().getCode())) {
            // 执行中的任务强制暂停
            runningTaskFutureMap.get(delayTask.getTaskDto().getCode()).cancel(true);
            runningTaskFutureMap.remove(delayTask.getTaskDto());
        }
        System.out.println("code=" + delayTask.getTaskDto().getCode() + "的延时任务已经取消！");
    }

    public void stop() {
        this.run = false;
        delayQueue.clear();
        runningTaskFutureMap.clear();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }
}
