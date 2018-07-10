package com.tievoli.sbfuse.biz.user.service;

import com.tievoli.sbfuse.biz.user.entity.User;
import com.tievoli.sbfuse.framework.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<User> {

    List<User> getUserList();

}
