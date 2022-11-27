package com.codermast.takeoutfood.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Description: 异常处理类
 * @author: CoderMast
 * @date: 2022/11/26
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * @Description: 异常处理
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        log.info("SQLIntegrityConstraintViolationException异常处理");
        log.info(exception.getMessage());

        // 异常信息中包含"Duplicate entry"则说明是唯一约束异常
        if (exception.getMessage().contains("Duplicate entry")) {
            String[] s = exception.getMessage().split(" ");
            String msg = s[2] + "用户名已存在";
            return R.error(msg);
        }
        return R.error("未知异常");
    }

    /**
     * @Description: 业务异常的全局处理
     * @param customException 业务异常
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @ExceptionHandler(CustomException.class)
    public R<String> CustomExceptionHandler(CustomException customException) {
        log.error("CustomException异常处理");
        return R.error(customException.getMessage());
    }
}