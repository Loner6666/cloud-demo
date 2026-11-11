package com.example.order.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description：
 * @Author：GuoFeng
 * @CreateTime：2026-11-11
 */
//@ResponseBody
//@ControllerAdvice
@RestControllerAdvice // 全局异常处理器，等同于 @ControllerAdvice + @ResponseBody
public class GlobalExceptionHander {

//    @ExceptionHandler(Throwable.class)
//    public String error(Throwable e) {
//        return "";
//    }

}
