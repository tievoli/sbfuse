package com.tievoli.sbfuse.biz.order.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单附件.
 * </p>
 *
 * @author sbfuse
 * @since 2018-07-11
 */
@Data
@Accessors(chain = true)
@TableName("xknf_order_attach")
public class OrderAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 订单ID
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 附件ID
     */
    @TableField("attachment_id")
    private Long attachmentId;

    /**
     * 类型
     */
    private String type;

    /**
     * 创建人
     */
    @TableField("create_id")
    private Long createId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("update_id")
    private Long updateId;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否删除 Y-删除，N-未删除
     */
    private String deleted;



}
