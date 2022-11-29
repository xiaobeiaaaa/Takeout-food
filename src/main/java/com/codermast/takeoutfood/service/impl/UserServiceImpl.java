package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.User;
import com.codermast.takeoutfood.mapper.UserMapper;
import com.codermast.takeoutfood.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务层
 * @author: CoderMast
 * @date: 2022/11/29
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
