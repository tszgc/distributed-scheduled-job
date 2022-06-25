package org.nina.dsj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述：任务类型枚举
 * 作者：zgc
 * 时间：2022/6/25 08:22
 */
@AllArgsConstructor
@Getter
public enum TaskTypeEnum {

    REALTIME(1, "实时执行"),
    CRON(2, "定期执行"),
    DELAY(3, "延时执行");

    private int type;
    private String text;

    public static TaskTypeEnum getType(int type) {
        for (TaskTypeEnum taskType : values()) {
            if (taskType.type == type) {
                return taskType;
            }
        }
        throw new RuntimeException("不支持的类型");
    }


}
