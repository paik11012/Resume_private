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
    @PostMapping("/edu/upload")
    public Map<String, Object> uploadEduDetails(@RequestBody Map<String, Object> requestDto) {
        String user_id = "test@ssafy.com";
        Users user = usersService.findById(user_id);

        Map<String, Object> res = new HashMap<>(); // result object

        // request object //
        Map map_edu = (Map) requestDto.get("education");
        Educations education = Educations.builder()
                .edu_school_name((String) map_edu.get("edu_school_name"))
                .edu_school_sort((String) map_edu.get("edu_school_sort"))
                .edu_school_st_date((String) map_edu.get("edu_school_st_date"))
                .edu_school_ed_date((String) map_edu.get("edu_school_ed_date"))
                .build();

        Map map_edu_detail = (Map) requestDto.get("education_detail");
        EducationDetails educationDetails = EducationDetails.builder()
                .edu_detail_major_sort((String) map_edu_detail.get("edu_detail_major_sort"))
                .edu_detail_credit(Long.valueOf((Integer) map_edu_detail.get("edu_detail_credit")))
                .edu_detail_grade((Double) map_edu_detail.get("edu_detail_grade"))
                .build();
        // END: request object //

        List<Educations> find = educationsService.findAll(user);
        if(find.size() == 0){ // 회원에 등록된 education 정보가 없으면 insert
            // Educations //
            education.setUser(user);
            Educations edu = educationsService.save(education);
            res.put("education", edu);

            // EducationDetails //
            educationDetails.setEducation(edu);
            EducationDetails edu_detail = educationDetailsService.save(educationDetails);
            res.put("education_detail", edu_detail);
        }
        else { // 회원에 등록된 education 정보가 있으면, 일치하는 정보를 찾아서 update
            for(Educations edu : find){
                if(edu.getEducation_id().equals(education.getEducation_id())){ // DB에서, client에서 넘어온 id와 동일한 id를 찾음
                    edu = educationsService.update(edu, education);
                    res.put("Educations", edu);

                    List<EducationDetails> edu_detail_list = educationDetailsService.findAll(edu);
                    for(EducationDetails ed : edu_detail_list){
                        if(ed.getEdu_detail_id().equals(educationDetails.getEdu_detail_id())){ // DB에서, client에서 넘어온 id와 동일한 id를 찾음
                            ed = educationDetailsService.update(ed, educationDetails);
                            res.put("EducationDetails", ed); break;
                        }
                    }
                    break;
                }
            }
        }
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