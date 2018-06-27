package com.tievoli.sbfuse.biz.user.controller;

import com.tievoli.sbfuse.biz.user.entity.User;
import com.tievoli.sbfuse.biz.user.service.UserService;
import com.tievoli.sbfuse.framework.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user/**")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result getUserById() {
        User user = new User();
        user.setId(100001l);
        user.setUsername("admin@admin.com");
        user.setName("test");
        user.setPassword("123456");
        user.setPhoneNumber("123456");
        user.setDeleted("N");
        user.setStatus("10");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.insert(user);
        return Result.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete() {
        User user = new User();
        user.setId(100001l);
        userService.deleteById(user);
        return Result.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update() {
        User user = new User();
        user.setId(100001l);
        user.setUsername("test@test.com");
        user.setName("test");
        user.setPassword("123456");
        user.setPhoneNumber("123456");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.updateById(user);
        return Result.success();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getUserList() {
        User user = new User();
        user.setId(100001l);
        return Result.success("00000", "成功", userService.selectById(user));
    }

}
