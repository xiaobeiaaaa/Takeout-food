package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.Category;
import com.codermast.takeoutfood.mapper.CategoryMapper;
import com.codermast.takeoutfood.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @Description: 分类服务的实现类
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
