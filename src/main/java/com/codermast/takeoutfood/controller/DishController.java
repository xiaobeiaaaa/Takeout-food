package com.codermast.takeoutfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codermast.takeoutfood.common.R;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 菜品管理控制器
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    // 注入dish业务实体
    @Autowired
    private DishService dishService;

    /**
     * @Description: 分页查询dish内容
     * @param page 页码
     * @param pageSize 页面大小
     * @param name 关键词
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page pageInfo = new Page(page,pageSize);

        // 构造条件过滤器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 构建查询条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);

        // 增加排序方法
        queryWrapper.orderByDesc(Dish::getSort);

        dishService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * @Description: 批量删除菜品
     * @param ids 要删除的菜品id
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @DeleteMapping
    public R<String> delete(String ids){
        String[] split = ids.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        dishService.removeBatchByIds(list);
        return R.success("批量删除成功！");
    }
}
