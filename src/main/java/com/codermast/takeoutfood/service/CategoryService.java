package com.codermast.takeoutfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codermast.takeoutfood.entity.Category;

/**
 * @Description: 分类服务接口
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
