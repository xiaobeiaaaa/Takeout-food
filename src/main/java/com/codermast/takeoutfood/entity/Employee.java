package com.codermast.takeoutfood.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

import java.util.Date;

/**
* 员工信息
 * **/
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
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 创建人
    */
    private Long createUser;
    /**
    * 修改人
    */
    private Long updateUser;
}
