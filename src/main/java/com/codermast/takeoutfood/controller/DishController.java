package com.codermast.takeoutfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codermast.takeoutfood.common.BaseContext;
import com.codermast.takeoutfood.common.R;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.service.DishFlavorService;
import com.codermast.takeoutfood.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    // 注入dishFlavor实体
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * @param page     页码
     * @param pageSize 页面大小
     * @param name     关键词
     * @Description: 分页查询dish内容
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);

        // 构造条件过滤器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 构建查询条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);

        // 增加排序方法
        queryWrapper.orderByDesc(Dish::getSort);

        dishService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * @param ids 要删除的菜品id
     * @Description: 批量删除菜品
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @DeleteMapping
    public R<String> delete(String ids) {
        String[] split = ids.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        dishService.removeBatchByIds(list);
        return R.success("批量删除成功！");
    }

    /**
     * @param id 要获取信息的id值
     * @Description: 根据id查询一个dish对象
     * @Author: CoderMast <a href="https://www.codermast.com/">codermast</a>
     */
    @GetMapping("/{id}")
    public R<Dish> getOne(@PathVariable String id) {
        // 构建查询器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        queryWrapper.eq(Dish::getId, id);

        // 获取查询值
        Dish dish = dishService.getOne(queryWrapper);
        return R.success(dish);
    }

    /**
     * @Description: 添加菜品
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto, HttpServletRequest request) {
        // 获取当前登录的用户id，并且存入当前线程中
        long id = (long) request.getSession().getAttribute("employee");
        BaseContext.setCurrentId(id);

        dishService.saveWithFlavor(dishDto);

        return R.success("菜品添加成功");
    }
}
