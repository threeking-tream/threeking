package com.threeking.service.user.base;

import com.threeking.service.user.common.APIResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ParamValidControllerAdvice {

    /**
     * 拦截MethodArgumentNotValidException类型的错误
     * 也就是我们定义Valid错误
     * 也可以做一些其他的错误拦截器
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse bindExceptionHandler(MethodArgumentNotValidException ex){
        return APIResponse.errorResp(ex.getBindingResult());
    }
}