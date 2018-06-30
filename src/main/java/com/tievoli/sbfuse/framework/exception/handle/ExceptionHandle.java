package com.tievoli.sbfuse.framework.exception.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tievoli.sbfuse.framework.ApplicationConfig;
import com.tievoli.sbfuse.framework.exception.*;
import com.tievoli.sbfuse.framework.exception.ErrorResult;
import com.tievoli.sbfuse.framework.http.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义异常处理器.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private ObjectMapper xssObjectMapper;

    @Autowired
    private ExceptionCors exceptionCors;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HttpServletResponse httpServletResponse;


    //异常处理
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception ex, WebRequest request) throws Exception {
        ResponseEntity<Object> objectResponseEntity = this.handleException(ex, request);
        return super.handleExceptionInternal(ex, null, objectResponseEntity.getHeaders(), objectResponseEntity.getStatusCode(), request);
    }

    //扩展父类的异常处理方法,自定义的异常在这里
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //CORS特殊处理
        exceptionCors.fixCors(httpServletResponse);
        //异常处理
        HttpStatus localHttpStatus = status;
        //错误结果
        ErrorResult errorResult = buildError(applicationConfig, ex);
        //自定义异常处理
        if (ex instanceof PermissionException) { //权限异常
            localHttpStatus = HttpStatus.FORBIDDEN;
        } else if (ex instanceof AuthException) { //认证异常
            localHttpStatus = HttpStatus.FORBIDDEN;
        } else if (ex instanceof ParameterValidException) { //参数校验异常
            localHttpStatus = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof RestClientResponseException) { //rest请求异常
            try {
                RestClientResponseException restClientResponseException = (RestClientResponseException) ex;
                String data = restClientResponseException.getResponseBodyAsString();
                if (StringUtils.isNotBlank(data)) {
                    RestResponse<String> child = xssObjectMapper.readValue(data, xssObjectMapper.getTypeFactory().constructParametricType(RestResponse.class, String.class));
                    errorResult.setChild(child);
                }
            } catch (IOException e) {
                throw new BizRuntimeException(e);
            }
        } else if (ex instanceof MethodArgumentNotValidException) { //参数校验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) ex;
            try {
                errorResult.setMessage(xssObjectMapper.writeValueAsString(methodArgumentNotValidException.getBindingResult().getAllErrors()));
            } catch (JsonProcessingException e) {
                throw new BizRuntimeException(e);
            }
        }
        RestResponse<String> restResponse = new RestResponse<>(localHttpStatus, errorResult);

        //发出通知

        log.error(restResponse.getId(), ex);

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 构造错误响应对象.
     *
     * @param applicationConfig 系统配置对象
     * @param throwable         错误对象
     * @return 返回错误响应对象
     */
    public static ErrorResult buildError(ApplicationConfig applicationConfig, Throwable throwable) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setType(throwable.getClass().getName());
        errorResult.setMessage(ExceptionUtils.getMessage(throwable));
        if (applicationConfig.isOutputExceptionStackTrace()) {
            errorResult.setExceptionStackTrace(ExceptionUtils.getStackTrace(throwable));
        }
        errorResult.setDate(new Date());
        return errorResult;
    }
}
