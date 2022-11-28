package com.codermast.takeoutfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Dish;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
}
