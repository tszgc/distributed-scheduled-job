package org.nina.dsj.manage.controller;

import org.nina.dsj.common.model.JSONResult;
import org.nina.dsj.common.vo.TaskQueryPageParam;
import org.nina.dsj.common.vo.TaskQueryPageVo;
import org.nina.dsj.manage.param.Exec;
import org.nina.dsj.manage.param.Save;
import org.nina.dsj.manage.param.TaskParam;
import org.nina.dsj.model.DsjTask;
import org.nina.dsj.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述：任务管理器
 * 作者：zgc
 * 时间：2022/6/24 15:59
 */
@RequestMapping("task/manage")
@RestController
public class TaskController {

    @Resource
    private ITaskService taskService;

    /**
     * 新增任务
     * @param task
     * @return
     */
    @PostMapping("save")
    public JSONResult<Void> save(@Validated(Save.class) @RequestBody TaskParam task) {
        DsjTask dsjTask = new DsjTask();
        BeanUtils.copyProperties(task, dsjTask);
        taskService.saveTask(dsjTask);
        return JSONResult.success();
    }

    /**
     * 分页查询所有任务
     * @param queryPageParam
     * @return
     */
    @PostMapping("page")
    public JSONResult<TaskQueryPageVo> queryPage(@RequestBody TaskQueryPageParam queryPageParam) {
        return JSONResult.success(taskService.queryPage(queryPageParam.getPageNum(), queryPageParam.getPageSize()));
    }

    /**
     * 删除任务
     * @param task
     * @return
     */
    @PostMapping("delete")
    public JSONResult<Void> delete(@Validated(Exec.class) @RequestBody TaskParam task) {
        taskService.deleteTask(task.getCode());
        return JSONResult.success();
    }

    /**
     * 执行任务
     * @param task
     * @return
     */
    @PostMapping("exec")
    public JSONResult<Void> exec(@Validated(Exec.class) @RequestBody TaskParam task) {
        taskService.execTask(task.getCode());
        return JSONResult.success();
    }

    /**
     * 取消任务
     * @param task
     * @return
     */
    public JSONResult<Void> cancel(@Validated(Exec.class) @RequestBody TaskParam task) {
        taskService.cancelTask(task.getCode());
        return JSONResult.success();
    }




}
