package org.nina.dsj.service;

import org.nina.dsj.common.enums.TaskStatusEnum;
import org.nina.dsj.common.vo.TaskQueryPageVo;
import org.nina.dsj.model.DsjTask;

/**
 * 描述：任务服务接口
 * 作者：zgc
 * 时间：2022/6/24 18:04
 */
public interface ITaskService {

    int saveTask(DsjTask dsjTask);

    int deleteTask(String code);

    void cancelTask(String code);

    DsjTask queryByCode(String code);

    DsjTask queryById(Integer id);

    TaskQueryPageVo queryPage(Integer pageNum, Integer pageSize);

    void execTask(String code);

    int statusZXZ(String code);

    int statusYZX(String code);

    int statusSC(String code);

    int statusZXSB(String code);

    int statusYQX(String code);

    int updateStatus(String code, TaskStatusEnum taskStatusEnum);
}
