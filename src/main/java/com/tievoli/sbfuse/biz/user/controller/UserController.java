package com.tievoli.sbfuse.biz.user.controller;

import com.tievoli.sbfuse.biz.user.entity.User;
import com.tievoli.sbfuse.biz.user.service.UserService;
import com.tievoli.sbfuse.framework.base.Result;
import com.tievoli.sbfuse.framework.exceptions.BizRuntimeException;
import com.tievoli.sbfuse.framework.exceptions.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * User Controller.
 */
@RestController
@RequestMapping("/user/**")
@Api(tags = "用户相关 接口")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取用户信息")
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
    @ApiOperation(value = "根据ID删除用户信息")
    public Result delete() {
        User user = new User();
        user.setId(100001l);
        userService.deleteById(user);
        return Result.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息")
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
    @ApiOperation(value = "获取用户信息列表")
    public Result getUserList() {
        User user = new User();
        user.setId(100001l);

        try{
            int a  = 1/0;
        }catch(Exception ex){
            throw new RuntimeException(ex);
//            throw BizRuntimeException.create(ErrorCode.USER_NOT_FOUND.getCode(),ErrorCode.USER_NOT_FOUND.getMessage());
        }

        return Result.success("00000", "成功", userService.selectById(user));
    }

}
