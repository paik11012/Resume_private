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
    public Careers update(String user_id, Careers new_career) {
        Careers career = careersRepository.findByUser(usersRepository.findById(user_id).get());
        new_career.setCareer_id(career.getCareer_id());

        return careersRepository.save(new_career);
    }
}
