package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Users;
import io.jsonwebtoken.*;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtService {
    private String salt = "mysalt";
    private Long expireMin = 100L;

    public String create(final Users user) {
        log.trace("time : {}", expireMin);
        final JwtBuilder builder = Jwts.builder();//토큰의 type으로 고정
        builder.setHeaderParam("typ", "JWT");
        builder.setSubject("로그인토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*expireMin))//토큰 유
                // 효시간 설정
                .claim("user_id" , user.getUser_id());

        //secret key를 이용한 암호화

        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());


        //마지막으로 직렬화 처리
        final String jwt = builder.compact();
        log.debug("토큰 발행 : {}", jwt);
        return jwt;
    }

    public void checkValid(final String jwt) {
        log.trace("토큰 점검 : {}", jwt);
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String, Object> get(final String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }
        log.trace("claims:{}", claims);
        return claims.getBody();
    }
}
