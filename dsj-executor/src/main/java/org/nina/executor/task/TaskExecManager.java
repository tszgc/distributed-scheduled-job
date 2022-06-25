package org.nina.executor.task;

import org.nina.dsj.common.dto.TaskDto;
import org.nina.executor.task.delay.DelayTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述：运行任务管理器
 * 作者：zgc
 * 时间：2022/6/25 08:41
 */
@Component
public class TaskExecManager {

    @Resource
    private DelayTaskExecutor delayTaskExecutor;

    /**
     * 提交延时任务
     * @param taskDto
     */
    public void submitDelay(TaskDto taskDto) {
        delayTaskExecutor.produce(taskDto);
    }

    public void cancelDelay(TaskDto taskDto) {
        delayTaskExecutor.cancel(taskDto);
    }

    public void startDelay() {
        delayTaskExecutor.start();
    }

    public void stopDelay() {
        delayTaskExecutor.stop();
    }

}
