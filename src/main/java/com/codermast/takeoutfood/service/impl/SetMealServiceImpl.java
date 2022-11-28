package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.dto.SetMealDto;
import com.codermast.takeoutfood.entity.Category;
import com.codermast.takeoutfood.entity.SetMeal;
import com.codermast.takeoutfood.entity.SetMealDish;
import com.codermast.takeoutfood.mapper.SetMealMapper;
import com.codermast.takeoutfood.service.CategoryService;
import com.codermast.takeoutfood.service.SetMealDishService;
import com.codermast.takeoutfood.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 套餐服务实现类
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Slf4j
@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal> implements SetMealService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SetMealDishService setMealDishService;

    @Override
    public SetMealDto getByIdWithSetMealDto(String id) {
        SetMeal setMeal = this.getById(id);

        SetMealDto setMealDto = new SetMealDto();
        BeanUtils.copyProperties(setMeal,setMealDto);

        Long categoryId = setMeal.getCategoryId();
        Category category = categoryService.getById(categoryId);
        setMealDto.setCategoryName(category.getName());

        LambdaQueryWrapper<SetMealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetMealDish::getDishId,id);

        List<SetMealDish> setMealDishes = setMealDishService.list(queryWrapper);
        setMealDto.setSetmealDishes(setMealDishes);
        return setMealDto;
    }

    @Override
    @Transactional
    public boolean updateWithDish(SetMealDto setMealDto) {
        this.updateById(setMealDto);
        List<SetMealDish> setmealDishes = setMealDto.getSetmealDishes();
        setmealDishes.forEach((item)->{
            item.setSetmealId(setMealDto.getId());
        });
        log.info(setmealDishes.toString());
        setMealDishService.saveOrUpdateBatch(setmealDishes);
        return true;
    }

    @Override
    @Transactional
    public boolean saveByIdWithSetMealDto(SetMealDto setMealDto) {
        this.save(setMealDto);

        List<SetMealDish> setmealDishes = setMealDto.getSetmealDishes();
        List<SetMealDish> collect = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setMealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setMealDishService.saveBatch(collect);

        return true;
    }
}
