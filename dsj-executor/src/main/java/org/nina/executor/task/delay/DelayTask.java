package org.nina.executor.task.delay;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.nina.dsj.common.dto.TaskDto;
import org.nina.dsj.common.util.HttpUtil;
import org.nina.dsj.service.ITaskService;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 描述：延时任务
 * 作者：zgc
 * 时间：2022/6/25 08:50
 */
@ToString
@Getter
public class DelayTask implements Delayed, Runnable {

    private TaskDto taskDto;

    private ITaskService taskService;

    public DelayTask(TaskDto taskDto, ITaskService taskService) {
        this.taskDto = taskDto;
    }

    private final long start = System.currentTimeMillis();

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(start + taskDto.getDelay() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed other) {
        if (other == this) {
            return 0;
        }
        DelayTask otherTask = (DelayTask) other;
        long d = this.getDelay(TimeUnit.MILLISECONDS) - otherTask.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(d, 0);
    }

    @Override
    public void run() {
        try {
            System.out.println("code=" + taskDto.getCode() + "的延时任务开始执行！");
            HttpUtil.doPostJSON(taskDto.getUrl(), taskDto.getParam());
            taskService.statusYZX(taskDto.getCode());
        } catch (Exception e) {
            System.out.println("执行任务发生异常！");
            taskService.statusZXSB(taskDto.getCode());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DelayTask delayTask = (DelayTask) o;

        return StringUtils.equals(this.getTaskDto().getCode(), delayTask.getTaskDto().getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getTaskDto().getCode());
    }
}
