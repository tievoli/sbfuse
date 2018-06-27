package com.tievoli.sbfuse.biz.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user_login")
public class User implements Serializable {

    @TableId
    private Long id;

    private String username;

    private String name;

    private String password;

    private String salt;

    @TableField("phone_number")
    private String phoneNumber;

    private String email;

    private String status;

    private String deleted;

    private String avatar;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
