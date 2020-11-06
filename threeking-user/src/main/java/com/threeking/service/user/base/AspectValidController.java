package com.threeking.service.user.base;


import com.threeking.service.user.common.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Validator;
import java.util.List;


/**
 * @Author: A.H
 * @Date: 2020/11/5 16:15
 */
//@Aspect
//@Component
//@Slf4j
public class AspectValidController {

    @Autowired
    private Validator validator;

    /**
     * 切入点
     */
    @Pointcut(value = "execution(* com.threeking.service.user.controller..*.*(..))")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object validateParameter(ProceedingJoinPoint joinPoint) throws Throwable {

        BindingResult bindingResult = null;
        for (Object arg : joinPoint.getArgs()) {//遍历被通知方法(controller方法)的参数列表
            if (arg instanceof BindingResult) {//参数校验结果会存放在BindingResult中
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult != null) {
            if (bindingResult.hasErrors()) {//检查是否存在校验错误

                String errorInfo = "";
                List<FieldError> errors = bindingResult.getFieldErrors();//获取字段参数不合法的错误集合
                for(FieldError error : errors){
                    errorInfo = errorInfo + "[" + error.getField() + " " + error.getDefaultMessage() + "]";
                }
                return APIResponse.errorResp(errorInfo);//返回异常错误


            }
        }
        //执行目标方法
        return joinPoint.proceed();
    }


}



