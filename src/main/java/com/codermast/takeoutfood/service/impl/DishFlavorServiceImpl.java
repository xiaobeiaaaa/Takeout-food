package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.DishFlavor;
import com.codermast.takeoutfood.mapper.DishFlavorMapper;
import com.codermast.takeoutfood.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @Description: 菜品口味实现类
 * @author: CoderMast
 * @date: 2022/11/28
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
