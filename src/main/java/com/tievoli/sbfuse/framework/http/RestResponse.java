package com.tievoli.sbfuse.framework.http;

import com.tievoli.sbfuse.framework.exception.ErrorResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * 自定义响应体.
 */
@Data
public class RestResponse<T> implements Serializable {

    //响应ID
    @ApiModelProperty(value = "响应ID", required = true, dataType = "string")
    private String id = UUID.randomUUID().toString();

    //响应代码
    @ApiModelProperty(value = "状态码(业务定义)", required = true, dataType = "string")
    private String code = Integer.toString(HttpStatus.OK.value());

    //响应描述
    @ApiModelProperty(value = "状态码描述(业务定义)", required = true, dataType = "string")
    private String message = HttpStatus.OK.getReasonPhrase();

    //响应结果
    @ApiModelProperty(value = "结果集(泛型)", required = true, dataType = "object")
    private T result = null;

    //错误结果
    @ApiModelProperty(value = "错误", required = true, dataType = "object")
    private ErrorResult error;

    //用于JSON使用
    public RestResponse() {
        super();
    }

    //场景1：直接返回结果对象
    public RestResponse(T result) {
        super();
        this.result = result;
    }

    //场景2：返回错误结果场景
    public RestResponse(HttpStatus httpStatus , ErrorResult errorResult){
        super();
        this.code = Integer.toString(httpStatus.value());
        this.message = httpStatus.getReasonPhrase();
        this.error = errorResult;
    }

    //场景3：返回带有结果的场景(Not Error)
    public RestResponse(String code, String message, T result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    //场景4：返回不带结果的场景(Not Error)
    public RestResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    //场景5: 可外部传入的异常场景
    public RestResponse(String code, String message, ErrorResult error) {
        super();
        this.code = code;
        this.message = message;
        this.error = error;
    }
}
