package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.CareersRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.CareersResponseDto;
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
    public CareersRepository careersRepository;

    @Autowired
    public UsersRepository usersRepository;

    @After
    public void afterTest() throws Exception{}

    @Test
    public void testRegistrationCareer() throws Exception{
        // given
        String career_myPic = "";
        String military_sort = "해군";
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

        String url = "http://localhost:" + port + "/careers/registration";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Careers career = careersRepository.findById(responseEntity.getBody()).get();
        assertThat(career.getMilitary_sort()).isEqualTo(military_sort);
        assertThat(career.getMilitary_st_date()).isEqualTo(military_st_date);
        assertThat(career.getMilitary_ed_date()).isEqualTo(military_ed_date);
        assertThat(career.getMilitary_class()).isEqualTo(military_class);
    }

    @Test
    public void testSelectOneCareer(){
        // given
        Users user = usersRepository.findById("test2").get();
        Careers career = careersRepository.findByUser(user);

        String url = "http://localhost:" + port + "/careers/select";

        // when
        ResponseEntity<Careers> responseEntity = restTemplate.getForEntity(url, Careers.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCareer_id()).isEqualTo(career.getCareer_id());
    }

    @Test
    public void testUpdateCareer() throws Exception{
        // given
        String user_id = "test";
        Careers career = careersRepository.findByUser(usersRepository.findById(user_id).get());
        String myPic = "myPic_update";
        String military_class = "병장_update";

        CareersResponseDto requestDto = CareersResponseDto.builder()
                .career_myPic(myPic)
                .military_sort(career.getMilitary_sort())
                .military_st_date(career.getMilitary_st_date())
                .military_ed_date(career.getMilitary_ed_date())
                .military_class(military_class)
                .build();

        String url = "http://localhost:" + port + "/careers/update";

        // when
        restTemplate.put(url, requestDto);

        // then
        career = careersRepository.findByUser(usersRepository.findById(user_id).get());
        assertThat(career.getCareer_myPic()).isEqualTo(myPic);
        assertThat(career.getMilitary_class()).isEqualTo(military_class);
    }
}
