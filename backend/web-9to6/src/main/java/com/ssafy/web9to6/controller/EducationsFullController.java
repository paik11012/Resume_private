package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.EducationDetails;
import com.ssafy.web9to6.domain.Educations;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.EducationFullResponseDto;
import com.ssafy.web9to6.dto.EducationsResponseDto;
import com.ssafy.web9to6.service.CareersService;
import com.ssafy.web9to6.service.EducationDetailsService;
import com.ssafy.web9to6.service.EducationsService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class EducationsFullController {
    private final EducationsService educationsService;
    private final CareersService careersService;
    private final UsersService usersService;
    private final EducationDetailsService educationDetailsService;

    @ApiOperation("학력 정보 등록/수정")
    @PostMapping("/edu/registration")
    public Map<String, Object> registrationEduDetails(@RequestBody EducationFullResponseDto requestDto) {
        String user_id = "daseul@ssafy.com";
        Users user = usersService.findById(user_id);

        // Education 처리 //
        Long edu_id = requestDto.getEdu_id();
        Optional<Educations> edu = educationsService.findById(edu_id);

        Educations new_edu;
        if (edu.isPresent()) { // 기존 education 정보가 존재하면 update
            new_edu = Educations.builder()
                    .edu_school_name(requestDto.getEdu_school_name())
                    .edu_school_st_date(requestDto.getEdu_school_st_date())
                    .edu_school_ed_date(requestDto.getEdu_school_ed_date())
                    .edu_school_sort(requestDto.getEdu_school_sort())
                    .build();
            new_edu.setEdu_id(edu_id); // 기존 education_id 연결
        } else { // 기존 education 정보가 없으면 insert
            new_edu = Educations.builder()
                    .edu_school_name(requestDto.getEdu_school_name())
                    .edu_school_st_date(requestDto.getEdu_school_st_date())
                    .edu_school_ed_date(requestDto.getEdu_school_ed_date())
                    .edu_school_sort(requestDto.getEdu_school_sort())
                    .build();
            new_edu.setUser(user);
        }
        new_edu = educationsService.save(new_edu);
        // END: Education 처리 //

        // Education Detail 처리 //
        Long edu_detail_id = requestDto.getEdu_detail_id();
        Optional<EducationDetails> edu_detail = educationDetailsService.findById(edu_detail_id);

        EducationDetails new_edu_detail;
        if (edu_detail.isPresent()) { // 기존 education detail 정보가 존재하면 update
            new_edu_detail = EducationDetails.builder()
                    .edu_detail_major_sort(requestDto.getEdu_detail_major_sort())
                    .edu_detail_credit(requestDto.getEdu_detail_credit())
                    .edu_detail_grade(requestDto.getEdu_detail_grade())
                    .build();
            new_edu_detail.setEduDetail_id(edu_detail_id);  // 기존 education_detail_id 연결
        } else { // 기존 education detail 정보가 없으면 insert
            new_edu_detail = EducationDetails.builder()
                    .edu_detail_major_sort(requestDto.getEdu_detail_major_sort())
                    .edu_detail_credit(requestDto.getEdu_detail_credit())
                    .edu_detail_grade(requestDto.getEdu_detail_grade())
                    .build();
        }
        new_edu_detail.setEducation(new_edu);
        educationDetailsService.save(new_edu_detail);
        // END: Education Detail 처리 //

        Map<String, Object> res = new HashMap<>();
        res.put("Educations", new_edu);
        res.put("EducationDetails", new_edu_detail);
        return res;
    }

    @ApiOperation("모든 학력 정보 조회")
    @GetMapping("/edu/findAll")
    public List<Map<Educations, List<EducationDetails>>> findAllEdu(){
        String user_id = "daseul@ssafy.com";
        Users user = usersService.findById(user_id);

        List<Map<Educations, List<EducationDetails>>> mapList = new LinkedList<>();
        List<Educations> eduList = educationsService.findAll(user);
        for(Educations edu : eduList){
            Map<Educations, List<EducationDetails>> tmpMap = new HashMap<>();
            List<EducationDetails> eduDetailList = educationDetailsService.findAll(edu);
            tmpMap.put(edu, eduDetailList);
        }

        return mapList;
    }

    @ApiOperation("학력 정보 한 개 조회")
    @GetMapping("edu/findOne/{edu_id}")
    public Map<Educations, List<EducationDetails>> findOne(@PathVariable String edu_id){
        Educations edu = educationsService.findById(Long.valueOf(edu_id)).get();
        List<EducationDetails> eduDetailList = educationDetailsService.findAll(edu);

        Map<Educations, List<EducationDetails>> res = new HashMap<>();
        res.put(edu, eduDetailList);

        return res;
    }

    @ApiOperation("학력 정보 삭제")
    @DeleteMapping("/edu/deleteOne/{edu_id}")
    public void deleteOneEdu(@PathVariable String edu_id){
        educationsService.deleteOne(Long.valueOf(edu_id));
    }
}