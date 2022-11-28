package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.entity.DishFlavor;
import com.codermast.takeoutfood.mapper.DishMapper;
import com.codermast.takeoutfood.service.DishFlavorService;
import com.codermast.takeoutfood.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 菜品管理服务实现类
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    DishFlavorService dishFlavorService;

    @Autowired
    DishService dishService;

    /**
     * @Description: 新建菜品和口味
     * @param dishDto 封装实体
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto){
        // 添加菜品
        dishService.save(dishDto);

        List<DishFlavor> flavors = dishDto.getFlavors();

        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDto.getId());
        }

        // 批量保存菜品偏好
        dishFlavorService.saveBatch(flavors);
    }
}
