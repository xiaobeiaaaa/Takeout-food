package com.codermast.takeoutfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codermast.takeoutfood.common.R;
import com.codermast.takeoutfood.entity.SetMeal;
import com.codermast.takeoutfood.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 套餐管理控制器
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    SetMealService setMealService;

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();

        Page pageInfo = new Page(page,pageSize);

        queryWrapper.like(StringUtils.isNotEmpty(name),SetMeal::getName,name);

        setMealService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
