package com.codermast.takeoutfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermast.takeoutfood.entity.Order;
import com.codermast.takeoutfood.mapper.OrderMapper;
import com.codermast.takeoutfood.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Description: 订单业务实现类
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
