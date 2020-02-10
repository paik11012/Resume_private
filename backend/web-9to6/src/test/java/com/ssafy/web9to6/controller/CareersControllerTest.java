package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.CareersRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.CareersResponseDto;
import com.ssafy.web9to6.service.CareersService;
import com.ssafy.web9to6.service.UsersService;
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

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CareersControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public CareersService careersService;

    @Autowired
    public UsersService usersService;

    @After
    public void afterTest() throws Exception{}

    @Test
    public void testUploadCareer() throws Exception{
        // given
        String career_myPic = "";
        String military_sort = "육군";
        String military_st_date = "2000.12.01";
        String military_ed_date = "2002.01.01";
        String military_class = "병장";

        CareersResponseDto requestDto = CareersResponseDto.builder()
                .career_myPic(career_myPic)
                .military_sort(military_sort)
                .military_st_date(military_st_date)
                .military_ed_date(military_ed_date)
                .military_class(military_class)
                .build();

        String url = "http://localhost:" + port + "/careers/upload";

        // when
        ResponseEntity<Careers> responseEntity = restTemplate.postForEntity(url, requestDto, Careers.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Careers career = responseEntity.getBody();
        assertThat(career.getMilitary_sort()).isEqualTo(military_sort);
        assertThat(career.getMilitary_st_date()).isEqualTo(military_st_date);
        assertThat(career.getMilitary_ed_date()).isEqualTo(military_ed_date);
        assertThat(career.getMilitary_class()).isEqualTo(military_class);
    }

    @Test
    public void testSelectOneCareer(){
        // given
        Users user = usersService.findById("test@ssafy.com");
        Careers career = careersService.findByUser(user);

        String url = "http://localhost:" + port + "/careers/findOne";

        // when
        ResponseEntity<Careers> responseEntity = restTemplate.getForEntity(url, Careers.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCareer_id()).isEqualTo(career.getCareer_id());
    }
}
