package com.nurd.project.gateway.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;


@Component
public class ClientHandlerInterceptor implements HandlerInterceptor {

   private final String tokenServer = "http://localhost:8000/test/user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Allow Swagger UI //
        if(handler instanceof HandlerMethod == false ||
                ((HandlerMethod) handler).getBeanType().getPackage().getName().endsWith("controller") == false)
            return true;

        // Required Bearer token at header for REST requests //
        var header = request.getHeader("authorization");
        if (header == null || header.isEmpty() || header.startsWith("Bearer") == false) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        URI uri = new URI(tokenServer);

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", header);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        response.setStatus(result.getStatusCodeValue());
       return 200 == result.getStatusCodeValue();
    }
}
