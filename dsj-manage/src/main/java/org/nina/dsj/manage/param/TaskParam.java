package org.nina.dsj.manage.param;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 描述：任务传输类
 * 作者：zgc
 * 时间：2022/6/24 19:03
 */
@NoArgsConstructor
@Data
public class TaskParam {
    @NotBlank(message = "code 不能为空")
    private String code;

    @NotBlank(message = "name 不能为空")
    private String name;

    /**
     * 任务类型：1-实时执行；2-cron执行
     */
    @NotNull(message = "type 不能为空")
    @Range(min = 1, max = 2, message = "type只能为1或者2")
    private Short type;

    private String cron;

    private String url;

    private String param;
}
