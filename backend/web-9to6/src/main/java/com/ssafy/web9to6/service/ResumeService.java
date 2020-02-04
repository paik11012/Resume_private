package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Licenses;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.ResumeRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.LicensesResponseDto;
import com.ssafy.web9to6.dto.ResumeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {

    @Autowired
    private ResumeRepository rr;

    public List<Resume> findAll(Users user) {
        return rr.findByUser(user);
    }

    public  Resume save(ResumeResponseDto lic) {
        return rr.save(lic.toEntity());
    }

    public void delete(Long id) {
        rr.deleteById(id);
    }
}
