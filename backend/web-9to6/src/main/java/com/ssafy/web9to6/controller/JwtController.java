package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.UsersResponseDto;
import com.ssafy.web9to6.service.JwtService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin
@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class JwtController {
    private final UsersService usersService;
    private final JwtService jwtService;

    @ApiOperation("회원 로그인")
    @PostMapping("/users/signin2")

    public ResponseEntity<Map<String, Object>> userSignIn(HttpServletResponse response, @RequestBody UsersResponseDto requestDto){
        Map<String, Object> resultmap = new HashMap<>();
        HttpStatus status = null;
        Users users = requestDto.toEntity();
        System.out.println("users" +  users.getUser_id());
        String res = usersService.signIn(users).toString();
        if(res.equals("true")){
//            HttpSession session = request.getSession(true);
//            session.setAttribute("user_id", users.getUser_id());
              String token = jwtService.create(users);
              response.setHeader("jwt-auth-token", token);
              response.setHeader("Access-Control-Allow-Origin","*");
              response.setHeader("Access-Control-Allow-Headers","Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
              response.setHeader("Access-Control-Max-Age","3600 ");
              response.setHeader("Access-Control-Allow-Methods","*");
              response.setHeader("Access-Control-Expose-Headers","jwt-auth-token");

            //  response.setHeader("   Access-Control-Expose-Headers","jwt-auth-token");
             // System.out.println(response.getHeader("jwt-auth-token"));
              //response.se

              //resultmap.put("response", response.);
              resultmap.put("data", users);
              resultmap.put("status", true);
            resultmap.put("jwt-auth-token", token);
        //    resultmap.put("reponse", response);
              status = HttpStatus.ACCEPTED;

              System.out.println(token);

           // return res;

        }
        //return "false";
        return new ResponseEntity<Map<String, Object>>(resultmap, status);
    }
}
