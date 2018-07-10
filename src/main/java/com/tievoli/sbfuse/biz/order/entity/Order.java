package com.tievoli.sbfuse.biz.order.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单对象.
 */
@Data
@ApiModel(value = "订单信息")
public class Order implements Serializable {

    private Long id;

    private String orderCode;
}
