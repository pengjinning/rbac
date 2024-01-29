/*
 * @Author: jack ning github@bytedesk.com
 * @Date: 2024-01-24 16:20:32
 * @LastEditors: jack ning github@bytedesk.com
 * @LastEditTime: 2024-01-29 14:23:04
 * @FilePath: /rbac/rbac-backend/src/main/java/com/imyuanxiao/rbac/controller/ResponseControllerAdvice.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.imyuanxiao.rbac.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imyuanxiao.rbac.annotation.NotResponseBody;
import com.imyuanxiao.rbac.enums.ResultCode;
import com.imyuanxiao.rbac.exception.ApiException;
import com.imyuanxiao.rbac.model.vo.ResultVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @description  全局统一响应
 * @author: <a href="https://github.com/imyuanxiao">imyuanxiao</a>
 **/
@RestControllerAdvice(basePackages = { "com.imyuanxiao.rbac.controller" })
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, 
            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        // If false, won't carry out beforeBodyWrite()
        // If the return type of the interface is already ResultVO, there is no need for additional operations. Return false.
        // If method has annotation @NotResponseBody, return false
        return !(returnType.getParameterType().equals(ResultVO.class) || returnType.hasMethodAnnotation(NotResponseBody.class)) ;
    }

    @Override
    public Object beforeBodyWrite(
            @Nullable Object data, 
            @NonNull MethodParameter returnType, 
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, 
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {

        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                throw new ApiException(ResultCode.FAILED, "返回String类型错误");
            }
        }
        return new ResultVO<>(data);
    }
}
