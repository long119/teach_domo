package com.mici.commonutils.exceptionhandler;

import com.mici.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *统一异常处理
 */
@ControllerAdvice
public class GlobalException {

    //指定异常的处理
    @ExceptionHandler(Exception.class)
    //异常返回响应
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("");

    }
}
