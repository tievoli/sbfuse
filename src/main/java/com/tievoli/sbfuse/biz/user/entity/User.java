package com.tievoli.sbfuse.biz.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_login")
@ApiModel("用户信息")
public class User implements Serializable {

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐值")
    private String salt;

    @TableField("phone_number")
    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("是否删除")
    private String deleted;

    @ApiModelProperty("头像")
    private String avatar;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;


}
