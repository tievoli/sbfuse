package com.tievoli.sbfuse.framework.exceptions;

public enum ErrorCode {

    USER_NOT_FOUND("10001", "未发现用户"),

    UNKONW("99999", "未知异常");

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
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
}
