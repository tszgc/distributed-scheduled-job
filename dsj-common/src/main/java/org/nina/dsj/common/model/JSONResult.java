package org.nina.dsj.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：接口统一实体类
 * 作者：zgc
 * 时间：2022/6/24 16:01
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JSONResult<T> {

    private boolean success;
    private String code;
    private String msg;
    private T data;

    public static <T> JSONResult<T> success() {
        return success(null);
    }

    public static <T> JSONResult<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> JSONResult<T> success(T data) {
        return success("", data);
    }

    public static <T> JSONResult<T> success(String msg, T data) {
        return success(true, "0000", msg, data);
    }

    public static <T> JSONResult<T> success(boolean success, String code, String msg, T data) {
        return new JSONResult<>(success, code, msg, data);
    }

    public static <T> JSONResult<T> fail(String code, String msg) {
        return new JSONResult<>(false, code, msg, null);
    }

}
