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
    private Long expireMin = 100L; //토큰 만료 시간 설정

    public String create(final Users user) {
        final JwtBuilder builder = Jwts.builder();//토큰의 type으로 고정
        builder.setHeaderParam("typ", "JWT");
        builder.setSubject("로그인토큰")
                .setExpiration(new Date(System.currentTimeMillis() + 40000*60*expireMin))//토큰 유효 시간 설정
                .claim("user_id" , user.getUser_id());//토큰에 들어갈 정보

        //secret key를 이용한 암호화

        builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
        System.out.println("id  " + user.getUser_id());

        //마지막으로 직렬화 처리
        final String jwt = builder.compact();
        log.debug("토큰 발행 : {}", jwt);
        return jwt;
    }

    public boolean checkValid(final String jwt, String user_id) {
        log.trace("토큰 점검 : {}", jwt);
        String dd = null;
        Date now = new Date();

        if(Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody().getExpiration().before(now)) { return false; }
        if (jwt.length() > 0) {
            String decode_user_id = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody().get("user_id").toString();
            System.out.println("프론트" + user_id);
            System.out.println("백앤드" + decode_user_id);
            if(decode_user_id.equals(user_id)) return true;
            else return false;
        }
        return false;
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
