package com.nurd.project.gateway.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;


@Component
public class ClientHandlerInterceptor implements HandlerInterceptor {

   private final String tokenServer = "http://localhost:8000/test/user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var header = request.getHeader("authorization");
        if (header == null || header.isEmpty() || header.startsWith("Bearer") == false)
            return false;

        URI uri = new URI(tokenServer);

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", header);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

       return 200 == result.getStatusCodeValue();
    }
}
