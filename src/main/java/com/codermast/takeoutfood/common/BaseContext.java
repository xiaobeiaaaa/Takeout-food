package com.codermast.takeoutfood.common;

/**
 * @Description: 基于ThreadLocal封装工具类，保存和获取当前登录用户id
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
public class BaseContext {
    // ThreadLocal中只能存储一个值。
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * @Description: 设置当前用户id
     * @param id 用户id
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * @Description: 获取当前用户id
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }

}
