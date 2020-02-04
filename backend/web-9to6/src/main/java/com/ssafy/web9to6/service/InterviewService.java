package com.ssafy.web9to6.service;


import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.InterviewResponseDto;
import com.ssafy.web9to6.dto.ResumeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InterviewService {

    @Autowired
    private InterviewRepository ir;

    public List<Interview> findAll(Users user) {
        return ir.findByUser(user);
    }

    public  Interview save(InterviewResponseDto lic) {
        return ir.save(lic.toEntity());
    }

    public void delete(Long id) {
        ir.deleteById(id);
    }
}
