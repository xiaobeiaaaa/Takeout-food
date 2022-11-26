package com.codermast.takeoutfood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermast.takeoutfood.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
