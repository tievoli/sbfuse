package com.tievoli.sbfuse.biz.order.service.impl;

import com.tievoli.sbfuse.biz.order.entity.Order;
import com.tievoli.sbfuse.biz.order.mapper.OrderMapper;
import com.tievoli.sbfuse.biz.order.service.OrderService;
import com.tievoli.sbfuse.framework.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

}
