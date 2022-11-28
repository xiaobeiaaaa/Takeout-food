package com.codermast.takeoutfood.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description: 公共字段处理器
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */

@Slf4j
@Component
public class MyMateObjectHandler implements MetaObjectHandler {
    /**
     * @Description: 插入操作，自动填充
     * @param metaObject 元对象
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入操作时，字段处理成功");
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    /**
     * @Description: 更新操作自动填充
     * @param metaObject 元对象
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新操作时，字段处理成功");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
