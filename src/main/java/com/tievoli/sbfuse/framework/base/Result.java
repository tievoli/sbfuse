package com.tievoli.sbfuse.framework.base;

import com.tievoli.sbfuse.framework.GlobalConstants;
import com.tievoli.sbfuse.framework.exceptions.ErrorCode;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一返回结果.
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "返回码", required = true)
    private String code;

    @ApiModelProperty("错误原因")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(GlobalConstants.RESPONSE_SUCCESS_CODE, GlobalConstants.RESPONSE_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(T data) {
        return success(GlobalConstants.RESPONSE_SUCCESS_CODE, GlobalConstants.RESPONSE_SUCCESS_MESSAGE, data);
    }

    public static <T> Result<T> success(String code, String message) {
        return success(code, message, null);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> error() {
        return error(ErrorCode.UNKONW.getCode(), ErrorCode.UNKONW.getMessage());
    }

    public static <T> Result<T> error(String code, String message) {
        return error(code, message, null);
    }

    public static <T> Result<T> error(String code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
