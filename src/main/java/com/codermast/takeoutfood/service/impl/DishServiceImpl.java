package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.entity.DishFlavor;
import com.codermast.takeoutfood.mapper.DishMapper;
import com.codermast.takeoutfood.service.CategoryService;
import com.codermast.takeoutfood.service.DishFlavorService;
import com.codermast.takeoutfood.service.DishService;
import org.springframework.beans.BeanUtils;
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
    CategoryService categoryService;
    /**
     * @Description: 根据id查菜品信息和对应的口味信息
     * @param id 菜品id
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @Override
    public DishDto getByIdWithFlavor(String id) {
        // 返回值dishDto
        DishDto dishDto = new DishDto();

        // 查询的菜品
        Dish dish = this.getById(id);

        // 查询菜品对应的口味列表
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);

        // 将菜品对应的属性赋值给返回对象
        BeanUtils.copyProperties(dish,dishDto);
        // 将口味列表赋值给返回对象
        dishDto.setFlavors(list);

        return dishDto;
    }

    /**
     * @Description: 新建菜品和口味
     * @param dishDto 封装实体
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto){
        // 添加菜品
        this.save(dishDto);
        // 偏好
        List<DishFlavor> flavors = dishDto.getFlavors();

        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishDto.getId());
        }

        // 批量保存菜品偏好
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public List<Dish> getListByCategoryIdWithDish(String categoryId) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Dish::getCategoryId,categoryId);

        return this.list(queryWrapper);
    }
}
