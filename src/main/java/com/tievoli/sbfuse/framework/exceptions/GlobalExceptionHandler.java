package com.tievoli.sbfuse.framework.exceptions;

import com.tievoli.sbfuse.framework.base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {

    /**
     * 处理异常.
     *
     * @return 返回JSON结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(HttpServletRequest request, Exception ex) {

        String code = ErrorCode.UNKONW.getCode();
        String message = "";

        if (ex instanceof BizRuntimeException) {
            BizRuntimeException exception = ((BizRuntimeException) ex);
            code = exception.getCode();
            message = exception.getMessage();
        } else {
            message = ex.getMessage();
        }

        return Result.error(code, message);
    }

}
