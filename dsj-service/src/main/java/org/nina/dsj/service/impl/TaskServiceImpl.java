package org.nina.dsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.nina.dsj.common.dto.TaskDto;
import org.nina.dsj.common.vo.TaskQueryPageVo;
import org.nina.dsj.mapper.DsjTaskMapper;
import org.nina.dsj.model.DsjTask;
import org.nina.dsj.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述：
 * 作者：zgc
 * 时间：2022/6/24 18:19
 */
@Service
public class TaskServiceImpl implements ITaskService {
    @Resource
    private DsjTaskMapper taskMapper;

    @Override
    public int saveTask(DsjTask dsjTask) {
        if (Objects.isNull(dsjTask)) {
            throw new NullPointerException("dsjTask为空！");
        }
        return taskMapper.insertSelective(dsjTask);
    }

    @Override
    public DsjTask queryByCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new NullPointerException("code 为空！");
        }
        return taskMapper.selectByCode(code);
    }

    @Override
    public DsjTask queryById(Integer id) {
        if (id == null) {
            throw new NullPointerException("id 为空！");
        }
        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaskQueryPageVo queryPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DsjTask> dsjTasks = taskMapper.selectPage();
        List<TaskDto> taskDtos = new ArrayList<>(dsjTasks.size());
        for (DsjTask dsjTask : dsjTasks) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(dsjTask, taskDto);
            taskDtos.add(taskDto);
        }
        PageInfo<DsjTask> pageInfo = PageInfo.of(dsjTasks);
        return new TaskQueryPageVo().setPageNum(pageInfo.getPageNum()).setPageSize(pageInfo.getPageSize()).setTasks(taskDtos).setTotal(pageInfo.getTotal());
    }
}
