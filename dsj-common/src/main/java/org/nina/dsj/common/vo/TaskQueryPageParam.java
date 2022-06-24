package org.nina.dsj.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述：分页查询参数
 * 作者：zgc
 * 时间：2022/6/24 19:34
 */
@NoArgsConstructor
@Setter
public class TaskQueryPageParam {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null || pageNum < 0 ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize < 0 ? 20 : pageSize;
    }
}
