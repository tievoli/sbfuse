package com.tievoli.sbfuse.biz.user.service;

import com.tievoli.sbfuse.framework.base.BaseService;

import java.util.List;

public interface UserService<User> extends BaseService<User> {

    List<User> getUserList();

}
