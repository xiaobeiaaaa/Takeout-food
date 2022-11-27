package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.mapper.DishMapper;
import com.codermast.takeoutfood.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @Description: 菜品管理服务实现类
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
