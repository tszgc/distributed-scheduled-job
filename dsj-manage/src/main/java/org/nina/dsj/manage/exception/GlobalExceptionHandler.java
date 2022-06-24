package org.nina.dsj.manage.exception;

import lombok.extern.slf4j.Slf4j;
import org.nina.dsj.common.model.JSONResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常以及系统异常处理
     * @param e 异常
     * @return JSON响应体
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONResult<Void> bizExceptionHandler(Exception e) {
        log.error("异常信息", e);
        return JSONResult.fail("500", null != e.getCause() ? e.getCause().getMessage() : e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JSONResult<Void> methodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("参数验证失败", e);
        return JSONResult.fail("400", e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(",")));
    }

}
