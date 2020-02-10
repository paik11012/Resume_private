package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.service.JwtService;
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
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("token");
            String user_id = request.getHeader("user_id");
            if (token != null && token.length() > 0 && jwtService.checkValid(token,user_id)) { //토큰 유효성 검사
                return true;
            } else {
                throw new RuntimeException("토큰 문제 발생"); //reponse로 에러 발생 메세지 보냄
            }


        }
    }

}
