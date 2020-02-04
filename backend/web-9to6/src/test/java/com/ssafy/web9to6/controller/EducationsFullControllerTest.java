package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Educations;
import com.ssafy.web9to6.domain.EducationsRepository;
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

import java.util.List;

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
    public void testRegistrationEdu(){
        // given
        String edu_school_name = "삼성대학원";
        String edu_school_st_date = "2017.03.01";
        String edu_school_ed_date = "2019.02.01";
        String edu_school_sort = "대학원";

        EducationsResponseDto requestDto = EducationsResponseDto.builder()
                .edu_school_name(edu_school_name)
                .edu_school_st_date(edu_school_st_date)
                .edu_school_ed_date(edu_school_ed_date)
                .edu_school_sort(edu_school_sort)
                .build();

        String url = "http://localhost:" + port + "/edu/registration";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Educations education = educationsRepository.findById(responseEntity.getBody()).get();

        assertThat(education.getEdu_school_name()).isEqualTo(edu_school_name);
        assertThat(education.getEdu_school_st_date()).isEqualTo(edu_school_st_date);
        assertThat(education.getEdu_school_ed_date()).isEqualTo(edu_school_ed_date);
        assertThat(education.getEdu_school_sort()).isEqualTo(edu_school_sort);
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
