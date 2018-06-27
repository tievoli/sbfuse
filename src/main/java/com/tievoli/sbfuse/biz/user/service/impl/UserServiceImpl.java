package com.tievoli.sbfuse.biz.user.service.impl;

import com.tievoli.sbfuse.biz.user.service.UserService;
import com.tievoli.sbfuse.framework.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl<User, UserMapper> extends BaseServiceImpl implements UserService {
}
