package org.nina.dsj.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatusEnum {
    DZX((short)1, "待执行"),
    ZXZ((short)2, "执行中"),
    YZX((short)3, "已执行"),
    SC((short)4, "删除"),
    ZXSB((short)5, "执行失败"),
    YQX((short)6, "已取消");

    private final short status;
    private final String text;

}
