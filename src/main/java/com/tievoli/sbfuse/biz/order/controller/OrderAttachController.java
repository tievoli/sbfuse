package com.tievoli.sbfuse.biz.order.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tievoli.sbfuse.biz.order.entity.OrderAttach;
import com.tievoli.sbfuse.biz.order.service.OrderAttachService;
import com.tievoli.sbfuse.framework.GlobalContants;
import com.tievoli.sbfuse.framework.http.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单附件控制器.
 * </p>
 *
 * @author sbfuse
 * @since 2018-07-11
 */
@RestController
@RequestMapping("/order/orderAttach")
@Api(tags = "订单附件相关接口")
public class OrderAttachController {

    @Autowired
    private OrderAttachService orderAttachService;

    /**
     * 获取订单附件列表.
     */
    @GetMapping(value = "/list")
    @ApiOperation("获取订单附件列表")
    public RestResponse<List<OrderAttach>> list() {
        return new RestResponse<List<OrderAttach>>(orderAttachService.selectList(Condition.EMPTY));
    }

    /**
     * 新增订单附件信息.
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增订单附件信息")
    public RestResponse<OrderAttach> add(OrderAttach orderAttach) {
        orderAttachService.insert(orderAttach);
        return new RestResponse<OrderAttach>();
    }

    /**
     * 删除订单附件信息.
     */
    @PostMapping(value = "/delete/{id}")
    @ApiOperation("删除订单附件信息")
    public RestResponse<OrderAttach> delete(@PathVariable("id") Long id) {
        orderAttachService.deleteById(id);
        return new RestResponse<OrderAttach>();
    }

    /**
     * 修改订单附件信息.
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改订单附件信息")
    public RestResponse<OrderAttach> update(OrderAttach orderAttach) {
        orderAttachService.updateById(orderAttach);
        return new RestResponse<OrderAttach>();
    }

    /**
     * 根据ID获取订单附件信息.
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation("根据ID获取订单附件信息")
    public RestResponse<OrderAttach> get(@PathVariable("id") Long id) {
        return new RestResponse<OrderAttach>(orderAttachService.selectById(id));
    }

    /**
     * 分页查询订单附件信息.
     */
    @GetMapping(value = "/selectOrderAttachPage")
    @ApiOperation("分页查询订单附件信息")
    public Page<OrderAttach> selectOrderAttachPage() {
        Page<OrderAttach> page = new Page<>(GlobalContants.defaultCurrentPage, GlobalContants.defaultPageSize);
        EntityWrapper<OrderAttach> wrapper = Condition.wrapper();
        return orderAttachService.selectPage(page, wrapper);
    }
}

