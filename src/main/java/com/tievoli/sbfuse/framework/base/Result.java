package com.tievoli.sbfuse.framework.base;

import java.io.Serializable;

/**
 * 统一返回结果.
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success("00000", "成功");
    }

    public static <T> Result<T> success(String code, String message) {
        return success(code, message, null);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> failure() {
        return failure("99999", "失败");
    }

    public static <T> Result<T> failure(String code, String message) {
        return failure(code, message, null);
    }

    public static <T> Result<T> failure(String code, String message, T data) {
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
