package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.UsersResponseDto;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UsersService usersService;
    // 수정수정
    @ApiOperation("회원 이메일(ID) 중복체크")
    @PostMapping("/users/checkId")
    public boolean userCheckId(@RequestBody UsersResponseDto requestDto){
        String user_id = requestDto.getUser_id();
        return usersService.checkId(user_id);
    }

    @ApiOperation("회원 등록")
    @PostMapping("/users/signup")
    public Users userSignUp(@RequestBody UsersResponseDto requestDto){
        Users user = requestDto.toEntity();
        user.setUser_authority("user");
        return usersService.save(user);
    }

    @ApiOperation("회원 로그인")
    @PostMapping("/users/signin")
    public String userSignIn(HttpServletRequest request, @RequestBody UsersResponseDto requestDto){
        Users users = requestDto.toEntity();
        String res = usersService.signIn(users).toString();
        if(res.equals("true")){
            HttpSession session = request.getSession(true);
            session.setAttribute("user_id", users.getUser_id());
            return res;
        }
        return "false";
    }

    @ApiOperation("회원 로그아웃")
    @GetMapping("/users/signout")
    public String userSignOut(HttpServletRequest request){
        return "false";
    }

    @ApiOperation("모든 회원 조회")
    @GetMapping("/users/findAll")
    public List<Users> userFindAll(HttpServletRequest request){
        String user_id = "admin@ssafy.com";
        if(user_id.equals("admin@ssafy.com")){
            return usersService.findAll();
        }
        return null;
    }

    @ApiOperation("회원 조회")
    @GetMapping("/users/findOne/{user_id}")
    public Users userFindOne(@PathVariable String user_id){
        return usersService.findById(user_id);
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/users/update")
    public Users userUpdate(HttpServletRequest request, @RequestBody UsersResponseDto requestDto){
        String user_id = "ds@ssafy.com";
        Users user = usersService.findById(user_id);
        return usersService.update(user, requestDto.toEntity());
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping("/users/delete")
    public void userDelete(HttpServletRequest request){
        String user_id = "test";
        usersService.delete(user_id);
    }

    @ApiOperation("회원 삭제 by admin")
    @DeleteMapping("/users/deleteByAdmin/{user_id}")
    public void userDeleteByAdmin(HttpServletRequest request, @PathVariable String user_id){
        String admin_id = "ds@ssafy.com";
        Users admin = usersService.findById(admin_id);
        if(admin.getUser_authority().equals("admin")){
            usersService.delete(user_id);
        }
    }

    @ApiOperation("회원 관리자 권한 부여")
    @PutMapping("/users/changeAuth/{user_id}")
    public Users userChangeAuth(@PathVariable String user_id){
        return usersService.updateAuth(user_id);
    }
}