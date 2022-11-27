package com.codermast.takeoutfood.common;

/**
 * @Description: 业务异常
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
