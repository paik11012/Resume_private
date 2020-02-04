package com.ssafy.web9to6.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CareersRepositoryTest {
    @Autowired
    CareersRepository careersRepository;

    @Autowired
    UsersRepository usersRepository;

    @PersistenceContext
    EntityManager em;

    @After
    public void afterTest(){}

    @Test
    public void testRegistrationCareer(){
        // given
        String career_myPic = "";
        String military_sort = "육군";
        String military_st_date = "2017.12.01";
        String military_ed_date = "2019.01.01";
        String military_class = "병장";

        careersRepository.save(Careers.builder()
                        .career_myPic(career_myPic)
                        .military_sort(military_sort)
                        .military_st_date(military_st_date)
                        .military_ed_date(military_ed_date)
                        .military_class(military_class)
                        .build());

        // when
        List<Careers> careerList = careersRepository.findAll();
        Careers career = careerList.get(careerList.size()-1);

        // then
        assertThat(career.getCareer_myPic()).isEqualTo(career_myPic);
        assertThat(career.getMilitary_sort()).isEqualTo(military_sort);
        assertThat(career.getMilitary_st_date()).isEqualTo(military_st_date);
        assertThat(career.getMilitary_ed_date()).isEqualTo(military_ed_date);
        assertThat(career.getMilitary_class()).isEqualTo(military_class);
    }

    @Test
    public void testSelectCareer(){
        // given
        String user_id = "test";
        Users user = usersRepository.findById(user_id).get();

        // when
        Careers career = careersRepository.findByUser(user);

        // then
        assertThat(career.getCareer_id()).isNotZero();
    }
}
