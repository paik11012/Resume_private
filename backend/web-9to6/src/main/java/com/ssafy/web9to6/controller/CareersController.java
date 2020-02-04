package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.CareersResponseDto;
import com.ssafy.web9to6.service.CareersService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class CareersController {

    private final CareersService careersService;
    private final UsersService usersService;

    @ApiOperation("회원 이력 등록")
    @PostMapping("/careers/registration")
    public Careers registrationCareer(HttpServletRequest request, @RequestBody CareersResponseDto requestDto){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        user_id = "daseul@ssafy.com";
        Users user = usersService.findById(user_id);

        Careers career = requestDto.toEntity();
        career.setUser(user);
        return careersService.save(career);
    }

    @ApiOperation("회원 이력 정보")
    @GetMapping("/careers/findOne")
    public Careers selectCareer(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
//        user_id = "test2";
        Users user = usersService.findById(user_id);
        return careersService.findByUser(user);
    }

    @ApiOperation("회원 이력 수정")
    @PutMapping("/careers/update")
    public Careers updateCareer(HttpServletRequest request, @RequestBody CareersResponseDto requestDto){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
//        user_id = "test";
        return careersService.update(user_id, requestDto.toEntity());
    }
}
