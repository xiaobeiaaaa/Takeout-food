package com.codermast.takeoutfood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codermast.takeoutfood.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
