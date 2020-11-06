package com.threeking.service.user.base;

import com.alibaba.druid.support.json.JSONUtils;
import com.threeking.service.user.common.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 拦截MethodArgumentNotValidException类型的错误
     * 也就是我们定义Valid错误
     * 也可以做一些其他的错误拦截器
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse<String> validExceptionHandler(MethodArgumentNotValidException ex){
        return APIResponse.errorResp(ex.getBindingResult());
    }

    /**
     * 处理运行异常
     */
    @ExceptionHandler(RuntimeException.class)
    public APIResponse<String> runtimeExceptionHandler(HttpServletRequest request, RuntimeException ex) {
        log.error("", ex);
        log.error("请求地址：" + request.getRequestURL());
        log.error("请求参数: " + JSONUtils.toJSONString(request.getParameterMap()));
        return APIResponse.errorResp(ex.getMessage());
    }

    /**
     * 用来捕获404，400这种无法到达controller的错误
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public APIResponse<String> exceptionHandler(Exception ex){
        log.error("", ex);
        if (ex instanceof NoHandlerFoundException) {
            return APIResponse.errorResp("404",ex.getMessage());
        } else {
            return APIResponse.errorResp("500",ex.getMessage());
        }
    }
}