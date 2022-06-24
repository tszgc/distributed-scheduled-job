package org.nina.dsj.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述：任务传输类
 * 作者：zgc
 * 时间：2022/6/24 19:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    private String code;
    private String name;
    /**
     * 任务类型：1-实时执行；2-cron执行
     */
    private Short type;
    private String cron;
    private String url;
    private String param;
    /**
     * 状态：1-待执行；2-执行中；3-已执行；4-删除；5执行失败
     */
    private Short status;
    private Date createTime;

}
