package org.nina.dsj.common.exception;

/**
 * 描述：任务异常
 * 作者：zgc
 * 时间：2022/6/25 11:06
 */
public class TaskException extends RuntimeException {
    public TaskException() {
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    public TaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
