package com.codermast.takeoutfood.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;

/**
 * @Description: 员工实体
 * @author: CoderMast
 * @date: 2022/11/26
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@TableName("employee")
@Data
public class Employee implements Serializable {

    /**
    * 主键
    */
    private Long id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 性别
    */
    private String sex;
    /**
    * 身份证号
    */
    private String idNumber;
    /**
    * 状态 0:禁用，1:正常
    */
    private Integer status;
    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    /**
    * 更新时间
    */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
    * 创建人
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long createUser;
    /**
    * 修改人
    */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;
}
