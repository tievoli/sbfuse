package com.tievoli.sbfuse.framework.exception;

import com.tievoli.sbfuse.framework.http.RestResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 自定义统一的Error类.
 */
@Data
public class ErrorResult implements Serializable {

    //异常类型名称
    @ApiModelProperty(value = "异常类型名称", required = true, dataType = "string")
    private String type;

    //异常信息
    @ApiModelProperty(value = "异常信息", required = true, dataType = "string")
    private String message;

    //异常堆栈
    @ApiModelProperty(value = "异常堆栈", required = true, dataType = "string")
    private String exceptionStackTrace;

    //异常发生时间
    @ApiModelProperty(value = "异常发生时间", required = true, dataType = "string")
    private Date date;

    //子异常
    @ApiModelProperty(value = "子异常", required = true, dataType = "object")
    private RestResponse<String> child;
}
