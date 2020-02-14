package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.CareersRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.CareersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CareersService {
    private final CareersRepository careersRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Careers save(Careers career) {
        return careersRepository.save(career);
    }

    @Transactional
    public Careers findByUser(Users user) {
        return careersRepository.findByUser(user);
    }

    @Transactional
    public Careers update(Careers career, Careers new_career) {
        career.setCareer_myPic(new_career.getCareer_myPic());
        career.setMilitary_sort(new_career.getMilitary_sort());
        career.setMilitary_st_date(new_career.getMilitary_st_date());
        career.setMilitary_ed_date(new_career.getMilitary_ed_date());
        career.setMilitary_class(new_career.getMilitary_class());
        career.setMemo(new_career.getMemo());

        return careersRepository.save(career);
    }
}
