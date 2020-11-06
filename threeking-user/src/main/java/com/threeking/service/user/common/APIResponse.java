package com.threeking.service.user.common;

import com.alibaba.nacos.common.utils.StringUtils;
import com.threeking.service.user.common.APIBaseResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class APIResponse<T> extends APIBaseResponse {

    private static final long serialVersionUID = 1L;


    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    /**
     * 请求成功返回的结构数据
     * @param t t
     * @return ResponseContentOne
     */
    public static <T> APIResponse<T> successResp(T t){
        APIResponse<T> resp = new APIResponse<T>();
        resp.setCode("0");
        resp.setMsg("success");
        resp.setContent(t);
        return resp;
    }

    public static <T> APIResponse<T> successResp(){
        APIResponse<T> resp = new APIResponse<T>();
        resp.setCode("0");
        resp.setMsg("success");
        return resp;
    }

    /**
     * 请求失败返回的数据
     * @param msg msg
     * @return ResponseContentOne
     */
    public static <T> APIResponse<T> errorResp(String msg){
        return errorResp("1", msg);
    }

    /**
     * 请求失败返回的数据
     * @param msg msg
     * @return ResponseContentOne
     */
    public static <T> APIResponse<T> errorResp(String code, String msg){
        APIResponse<T> resp = new APIResponse<>();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> APIResponse<T> errorResp(BindingResult result){

        APIResponse<T> resp = new APIResponse<T>();
        resp.setCode("1");
        StringBuilder sb = new StringBuilder("");
        result.getAllErrors().stream()
                .forEach(err -> {
                    sb.append(((FieldError) err).getField())
                            .append(":")
                            .append(err.getDefaultMessage())
                            .append(" | ");
                });
        String errMsg = sb.toString().trim();
        errMsg = errMsg.substring(0, errMsg.lastIndexOf("|"));
        resp.setMsg(errMsg);
        return resp;
    }


    public static <T> APIResponse<T>  errorRes(BindingResult bindingResult) {
        APIResponse<T> resp = new APIResponse<>();
        List<ObjectError> ls=bindingResult.getAllErrors();
        resp.setCode("1");
        resp.setMsg(ls.get(0).getDefaultMessage());
        return resp;
    }

    public static <T> APIResponse<T> httpCodeResp(HttpCode httpCode){
        APIResponse<T> resp = new APIResponse<T>();
        resp.setCode(httpCode.getCode());
        resp.setMsg(httpCode.getDesc());
        return resp;
    }
}