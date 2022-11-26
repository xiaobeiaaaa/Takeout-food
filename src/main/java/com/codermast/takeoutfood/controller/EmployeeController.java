package com.codermast.takeoutfood.controller;

import com.codermast.takeoutfood.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户控制器
 * @author: CoderMast
 * @date: 2022/11/26
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
}
