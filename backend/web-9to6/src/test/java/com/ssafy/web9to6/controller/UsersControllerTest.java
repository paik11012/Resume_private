package com.ssafy.web9to6.controller;

import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.UsersResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public UsersRepository usersRepository;

    @Test
    public void testCheckIdUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        String url = "http://localhost:" + port + "/users/checkId";

        UsersResponseDto requestDto = UsersResponseDto.builder()
                .user_id(user_id)
                .build();

        // when
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, requestDto, Boolean.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(false);
    }

    @Test
    public void testSignUpUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        String user_password = "ds";
        String user_name = "da";
        String user_phone = "010-0000-0000";

        UsersResponseDto requestDto = UsersResponseDto.builder()
                .user_id(user_id)
                .user_password(user_password)
                .user_name(user_name)
                .user_phone(user_phone)
                .build();

        String url = "http://localhost:" + port + "/users/signup";

        // when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Users user = usersRepository.findById(user_id).get();
        assertThat(user.getUser_id()).isEqualTo(user_id);
        assertThat(user.getUser_password()).isEqualTo(user_password);
        assertThat(user.getUser_name()).isEqualTo(user_name);
        assertThat(user.getUser_phone()).isEqualTo(user_phone);
    }

    @Test
    public void testSignInUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        String user_password = "ds";

        UsersResponseDto requestDto = UsersResponseDto.builder()
                .user_id(user_id)
                .user_password(user_password)
                .build();

        String url = "http://localhost:" + port + "/users/signin";

        // when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("true");
    }

    @Test
    public void testSignOutUsers() throws Exception{
        // given
        String url = "http://localhost:" + port + "/users/signout";

        // when
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("false");
    }

    @Test
    public void testFindAllUsers() throws Exception{
        // given
        String url = "http://localhost:" + port + "/users/findAll";

        // when
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testFindOneUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        UsersResponseDto requestDto = UsersResponseDto.builder()
                .user_id(user_id)
                .build();

        String url = "http://localhost:" + port + "/users/findOne/" + user_id;

        // when
        ResponseEntity<Users> responseEntity = restTemplate.getForEntity(url, Users.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUser_id()).isEqualTo(user_id);
    }

    @Test
    public void testUpdateUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        Users user = usersRepository.findById(user_id).get();

        String user_password = "ds_update";
        String user_name = "ds_update";
        String user_phone = "010-update-update";

        UsersResponseDto requestDto = UsersResponseDto.builder()
                .user_password(user_password)
                .user_name(user_name)
                .user_phone(user_phone)
                .build();

        String url = "http://localhost:" + port + "/users/update";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UsersResponseDto> requestEntity = new HttpEntity<>(requestDto, headers);

        // when
        ResponseEntity<Users> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Users.class);

        // then
        user = responseEntity.getBody();
        assertThat(user.getUser_password()).isEqualTo(user_password);
        assertThat(user.getUser_name()).isEqualTo(user_name);
        assertThat(user.getUser_phone()).isEqualTo(user_phone);
        assertThat(user.getModifiedDate().isAfter(user.getSignUpDate()));
    }

    @Test
    public void testDeleteUser() throws Exception{
        // given
        int before = usersRepository.findAll().size();
        String url = "http://localhost:" + port + "/users/delete";

        // when
        restTemplate.delete(url);

        // then
        int after = usersRepository.findAll().size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    public void testDeleteByAdminUser() throws Exception{
        // given
        int before = usersRepository.findAll().size();
        String user_id = "ee";
        String url = "http://localhost:" + port + "/users/deleteByAdmin/" + user_id;

        // when
        restTemplate.delete(url);

        int after = usersRepository.findAll().size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    public void testChangeAuthUser() throws Exception{
        // given
        String user_id = "ds@ssafy.com";
        String url = "http://localhost:" + port + "/users/changeAuth/" + user_id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(user_id, headers);

        // when
        ResponseEntity<Users> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Users.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUser_authority()).isEqualTo("admin");
    }
}
