package com.tievoli.sbfuse.biz.user.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tievoli.sbfuse.biz.user.mapper.UserMapper;
import com.tievoli.sbfuse.biz.user.service.UserService;
import com.tievoli.sbfuse.framework.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl<User> extends BaseServiceImpl implements UserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "user")
    public List getUserList() {
//        System.out.println("XXXX");
        return userMapper.selectList(Condition.EMPTY);
    }
}
