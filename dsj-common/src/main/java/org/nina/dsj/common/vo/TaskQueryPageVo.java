package org.nina.dsj.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.nina.dsj.common.dto.TaskDto;

import java.util.List;

/**
 * 描述：任务分页查询结果
 * 作者：zgc
 * 时间：2022/6/24 19:30
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskQueryPageVo {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<TaskDto> tasks;
}
