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

    public Interview update(Interview old, Interview update) {
        old.setInterview_answer(update.getInterview_answer());
        old.setInterview_company(update.getInterview_company());
        old.setInterview_date(update.getInterview_date());
        old.setInterview_memo(update.getInterview_memo());
        old.setInterview_task(update.getInterview_task());
        old.setInterview_question(update.getInterview_question());
        return ir.save(old);
    }

    public Interview findById(Long id) {
        return ir.findById(id).get();
    }
}
