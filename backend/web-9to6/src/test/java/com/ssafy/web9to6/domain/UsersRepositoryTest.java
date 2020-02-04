package com.ssafy.web9to6.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @After // 단위 테스트 끝난 후에 실행될 메소드 지정. 없으면 안 끝남
    public void afterTest(){ }

    @Test
    public void testRegistrationUser(){
        // given
        String user_id = "test3@ssafy.com";
        String user_password = "3333";
        String user_name = "test3";
        String user_phone = "010-6666-7777";

        usersRepository.save(Users.builder().user_id(user_id)
                                            .user_password(user_password)
                                            .user_name(user_name)
                                            .user_phone(user_phone)
                                            .build());

        // when
        List<Users> usersList = usersRepository.findAll();

        // then
        int pos = usersList.size() - 1;
        Users user = usersList.get(pos);
        assertThat(user.getUser_id()).isEqualTo(user_id);
        assertThat(user.getUser_password()).isEqualTo(user_password);
        assertThat(user.getUser_name()).isEqualTo(user_name);
        assertThat(user.getUser_phone()).isEqualTo(user_phone);
    }

    @Test
    public void testSelectUser(){
        // given
        String user_id = "test3";

        // when
        Users user = usersRepository.findById(user_id).get();

        // then
        assertThat(user.getUser_id()).isEqualTo(user_id);
    }

    @Test
    public void testUpdateUser(){
        // given
        String user_id = "test3";
        Users user = usersRepository.findById(user_id).get();

        String user_password = "1111_update";
        String user_name = user.getUser_name();
        String user_phone = user.getUser_phone();

        // when
        usersRepository.save(Users.builder().user_id(user_id)
                .user_password(user_password)
                .user_name(user_name)
                .user_phone(user_phone)
                .build());


        // then
        user = usersRepository.findById(user_id).get();
        assertThat(user.getUser_id()).isEqualTo(user_id);
        assertThat(user.getUser_password()).isEqualTo(user_password);
        assertThat(user.getUser_name()).isEqualTo(user_name);
        assertThat(user.getUser_phone()).isEqualTo(user_phone);
    }
}
