package org.nina.executor.controller;

import org.nina.dsj.common.dto.TaskDto;
import org.nina.dsj.common.model.JSONResult;
import org.nina.executor.task.TaskExecManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述：任务执行controller
 * 作者：zgc
 * 时间：2022/6/25 08:30
 */
@RequestMapping("/task/exec")
@RestController
public class TaskExecController {

    @Resource
    private TaskExecManager taskExecManager;

    @PostMapping("realtime")
    public JSONResult realtimeTask(@RequestBody TaskDto taskDto) {

        return JSONResult.success(null);
    }

    @PostMapping("delay")
    public JSONResult<Void> delayTaskExec(@RequestBody TaskDto taskDto) {
        taskExecManager.submitDelay(taskDto);
        return JSONResult.success(null);
    }

    @PostMapping("delay/cancel")
    public JSONResult<Void> delayTaskCancel(@RequestBody TaskDto taskDto) {
        taskExecManager.cancelDelay(taskDto);
        return JSONResult.success(null);
    }

    @PostMapping("cron")
    public JSONResult cronTask(@RequestBody TaskDto taskDto) {

        return JSONResult.success(null);
    }

}
