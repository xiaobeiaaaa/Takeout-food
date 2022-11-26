package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.Employee;
import com.codermast.takeoutfood.mapper.EmployeeMapper;
import com.codermast.takeoutfood.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EmployeeServiceImpl
 * @Description:  用户服务实现类
 * @author: codermast
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {
}
