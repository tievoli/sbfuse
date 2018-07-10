package com.tievoli.sbfuse.biz.order.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tievoli.sbfuse.biz.order.entity.Order;
import com.tievoli.sbfuse.biz.order.service.OrderService;
import com.tievoli.sbfuse.framework.http.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "测试订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("测试get方法")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public RestResponse<List<Order>> get(){
        return new RestResponse<>(orderService.selectList(null));
    }

}
