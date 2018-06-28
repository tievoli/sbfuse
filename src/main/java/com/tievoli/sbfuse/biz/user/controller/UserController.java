package com.tievoli.sbfuse.biz.user.controller;

import com.tievoli.sbfuse.biz.user.entity.User;
import com.tievoli.sbfuse.biz.user.service.UserService;
import com.tievoli.sbfuse.framework.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器.
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关 接口")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存用户信息")
    public Result save(User user) {
        userService.insert(user);
        return Result.success();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据ID删除用户信息")
    public Result delete(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        userService.deleteById(user);
        return Result.success();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息")
    public Result update(User user) {
        userService.updateById(user);
        return Result.success();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户Id获取用户信息")
    public Result getUserById(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        return Result.success(userService.selectById(user));
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息列表")
    public Result getUserList() {
        return Result.success(userService.getUserList());
    }

}
