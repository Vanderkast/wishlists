package net.vanderkast.wishlists.server.rest;

import net.vanderkast.wishlists.server.contract.OrError;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class OrErrorResponseAdvise<T> implements ResponseBodyAdvice<OrError<T>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        var method = returnType.getMethod();
        if (method == null)
            return false;
        return method.getReturnType().isAssignableFrom(OrError.class);
    }

    @Override
    public OrError<T> beforeBodyWrite(OrError<T> body,
                                      MethodParameter returnType,
                                      MediaType selectedContentType,
                                      Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                      ServerHttpRequest request,
                                      ServerHttpResponse response) {
        if (body != null && body.isError())
            response.setStatusCode(HttpStatus.BAD_REQUEST);
        return body;
    }
}
