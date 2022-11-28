package com.codermast.takeoutfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codermast.takeoutfood.common.BaseContext;
import com.codermast.takeoutfood.common.R;
import com.codermast.takeoutfood.dto.DishDto;
import com.codermast.takeoutfood.entity.Category;
import com.codermast.takeoutfood.entity.Dish;
import com.codermast.takeoutfood.service.CategoryService;
import com.codermast.takeoutfood.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    // 注入category实体
    @Autowired
    private CategoryService categoryService;

    /**
     * @param page     页码
     * @param pageSize 页面大小
     * @param name     关键词
     * @Description: 分页查询dish内容
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @GetMapping("/page")
    public R<Page<DishDto>> page(int page, int pageSize, String name) {
        // 菜品分页页面
        Page<Dish> dishPage = new Page<>(page,pageSize);
        // 菜品分页交互对象页面
        Page<DishDto> dishDtoPage = new Page<>();

        // 构造条件过滤器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 构建查询条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        // 分页查询菜品
        dishService.page(dishPage, queryWrapper);

        // 菜品分页记录值
        List<Dish> recordsDish = dishPage.getRecords();

        // 菜品分页交互对象记录值
        List<DishDto> recordsDishDto = recordsDish.stream().map((item) -> {
            // 创建dishDto对象
            DishDto dishDto = new DishDto();

            // 将Dish类型的item属性赋值到dishDto
            BeanUtils.copyProperties(item,dishDto);

            // 获取对象的分类id
            Long categoryId = dishDto.getCategoryId();

            // 根据分类id查分类对象
            Category categoryServiceById = categoryService.getById(categoryId);

            // 从分类对象中取出分类名称
            String categoryServiceByIdName = categoryServiceById.getName();

            // 设置分类名称到dishDto对象
            dishDto.setCategoryName(categoryServiceByIdName);


            // 返回该对象
            return dishDto;
        }).collect(Collectors.toList());

        // 将封装好的记录值赋值给dishDtoPage对象
        dishDtoPage.setRecords(recordsDishDto);
        // 将总条数赋值给dishDtoPage
        dishDtoPage.setTotal(dishPage.getTotal());
        return R.success(dishDtoPage);
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
    public R<DishDto> getOne(@PathVariable String id) {
       return R.success(dishService.getByIdWithFlavor(id));
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

    /**
     * @Description: 修改菜品信息
     * @param dishDto 菜品的信息
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto,HttpServletRequest request){
        long id = (long) request.getSession().getAttribute("employee");
        BaseContext.setCurrentId(id);
        boolean ret = dishService.updateById(dishDto);
        return ret? R.success("更新成功"):R.error("更新失败");
    }

    /**
     * @Description: 停售和启售
     * @param status : 状态码，0为停售，1为启售
     * @param ids 操作菜品的id列表
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable Integer status,@RequestParam List<Long> ids,HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute("employee");
        BaseContext.setCurrentId(id);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.in(ids != null,Dish::getId,ids);

        List<Dish> list = dishService.list(queryWrapper);

        for (Dish dish : list) {
            if (dish != null){
                dish.setStatus(status);
                dishService.updateById(dish);
            }
        }
        return R.success(status == 1? "启售成功" : "停售成功");
    }

    /**
     * @Description: 根据分类id查询其下的菜品
     * @param categoryId 分类id
     * @Author: <a href="https://www.codermast.com/">CoderMast</a>
     */
    @GetMapping("/list")
    public R<List<Dish>> getListByCategoryIdWithDish(String categoryId){
        return R.success(dishService.getListByCategoryIdWithDish(categoryId));
    }
}
