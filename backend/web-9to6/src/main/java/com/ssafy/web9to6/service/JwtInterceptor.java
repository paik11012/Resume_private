package com.ssafy.web9to6.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Enumeration;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //      String token = request.getHeader("token");
//        System.out.print(jwtService.checkValid(token));
//                return  true;

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("token");
            if (token != null && token.length() > 0) {
                jwtService.checkValid(token);
                log.trace("토큰 사용 가능 {} + ", token);
                return true;
            } else {
                throw new RuntimeException("인증 토큰이 없습니다. ");
            }


        }
    }

}
