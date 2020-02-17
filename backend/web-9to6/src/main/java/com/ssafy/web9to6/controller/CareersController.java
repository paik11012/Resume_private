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

    @ApiOperation("회원 이력 등록/수정")
    @PostMapping("/careers/upload")
    public Careers uploadCareer(HttpServletRequest request, @RequestBody CareersResponseDto requestDto){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);

        Careers career = requestDto.toEntity();
        Careers find = careersService.findByUser(user);
        if(find==null){
            career.setUser(user);
            return careersService.save(career);
        }
        else{ return careersService.update(find, career); }

    }

    @ApiOperation("회원 이력 정보")
    @GetMapping("/careers/findOne")
    public Careers selectCareer(HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);
        return careersService.findByUser(user);
    }

    @ApiOperation("회원 사진 등록/수정")
    @PostMapping("/careers/uploadPic")
    public Careers uploadPicCareer(HttpServletRequest request, @RequestBody CareersResponseDto requestDto){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);
        Careers career = careersService.findByUser(user);
        career.setCareer_myPic(requestDto.getCareer_myPic());
        return careersService.save(career);
    }

    @ApiOperation("회원 사진 다운로드")
    @GetMapping("/careers/downloadFile")
    public String downloadPicCareer(HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        Careers career = careersService.findByUser(usersService.findById(user_id));
        return career.getCareer_myPic();
    }
}
