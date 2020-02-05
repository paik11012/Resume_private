package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.EducationDetails;
import com.ssafy.web9to6.domain.Educations;
import com.ssafy.web9to6.domain.EducationsRepository;
import com.ssafy.web9to6.dto.EducationDetailsResponseDto;
import com.ssafy.web9to6.dto.EducationsResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationsFullControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public EducationsRepository educationsRepository;

    @After
    public void afterTest() throws Exception {}

    @Test
    public void testUploadEduDetails(){
        // given
        // Educations //
        String edu_school_name = "삼성고등학교";
        String edu_school_st_date = "2010.03.01";
        String edu_school_ed_date = "2013.02.01";
        String edu_school_sort = "고등학교";

        EducationsResponseDto requestDtoEdu = EducationsResponseDto.builder()
                .edu_school_name(edu_school_name)
                .edu_school_st_date(edu_school_st_date)
                .edu_school_ed_date(edu_school_ed_date)
                .edu_school_sort(edu_school_sort)
                .build();
        // END: Educations //

        // Educations Details //
        String edu_det_sort = "전공";
        String edu_det_credit = "140";
        String edu_det_grade = "3.5";

        EducationDetailsResponseDto requestDtoEduDetail = EducationDetailsResponseDto.builder()
                .edu_detail_major_sort(edu_det_sort)
                .edu_detail_credit(Long.parseLong(edu_det_credit))
                .edu_detail_grade(Double.parseDouble(edu_det_grade))
                .build();
        // END: Educations Details //

        Map<String, Object> map = new HashMap<>();
        map.put("education", requestDtoEdu);
        map.put("education_detail", requestDtoEduDetail);

        String url = "http://localhost:" + port + "/edu/upload";

        // when
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, map, Map.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

//        Map<String, Object> res = (Map) responseEntity.getBody();
//        Educations res_edu = (Educations) res.get("education");
//        EducationDetails res_edu_det = (EducationDetails) res.get("education_detail");
//
//        // related Educations //
//        assertThat(res_edu.getEdu_school_name()).isEqualTo(edu_school_name);
//        assertThat(res_edu.getEdu_school_st_date()).isEqualTo(edu_school_st_date);
//        assertThat(res_edu.getEdu_school_ed_date()).isEqualTo(edu_school_ed_date);
//        assertThat(res_edu.getEdu_school_sort()).isEqualTo(edu_school_sort);
//
//        // related EducationDetails //
//        assertThat(res_edu_det.getEdu_detail_major_sort()).isEqualTo(edu_det_sort);
//        assertThat(res_edu_det.getEdu_detail_credit()).isEqualTo(edu_det_credit);
//        assertThat(res_edu_det.getEdu_detail_grade()).isEqualTo(edu_det_grade);
    }

    @Test
    public void testFindAllEdu() throws Exception{
        // given
        String url = "http://localhost:" + port + "/edu/findAll";

        // when
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        for(int i = 0; i < responseEntity.getBody().size(); i++)
//        System.out.println(responseEntity.getBody().get(i).toString());
    }

    @Test
    public void testDeleteOneEdu() throws Exception{
        // given
        int before = educationsRepository.findAll().size();

        String edu_id = "3";
        String url = "http://localhost:" + port + "/edu/deleteOne/" + edu_id;

        // when
        restTemplate.delete(url);

        // then
        int after = educationsRepository.findAll().size();
        assertThat(before).isGreaterThan(after);
    }
}
