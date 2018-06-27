package com.tievoli.sbfuse.framework.base;

import java.io.Serializable;

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
        return success("00000", "成功", null);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        return new Result(code, message, data);
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
