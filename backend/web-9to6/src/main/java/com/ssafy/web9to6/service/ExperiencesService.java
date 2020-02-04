package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.ExperiencesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExperiencesService {

    @Autowired
    private ExperiencesRepository er;

    public List<Experiences> findAll(Users user) {
        return er.findByUser(user);
    }

    public  Experiences save(ExperiencesResponseDto exp) {
        return er.save(exp.toEntity());
    }

    public void delete(Long id) {
        er.deleteById(id);
    }
}
