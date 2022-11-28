package com.codermast.takeoutfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    DishDto getByIdWithFlavor(String id);
    void saveWithFlavor(DishDto dishDto);

    List<Dish> getListByCategoryIdWithDish(String categoryId);
}
