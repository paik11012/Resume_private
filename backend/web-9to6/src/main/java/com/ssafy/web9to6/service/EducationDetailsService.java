package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.EducationDetails;
import com.ssafy.web9to6.domain.EducationDetailsRepository;
import com.ssafy.web9to6.domain.Educations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EducationDetailsService {
    private final EducationDetailsRepository educationDetailsRepository;

    @Transactional
    public EducationDetails save(EducationDetails edu_detail) {
        return educationDetailsRepository.save(edu_detail);
    }

    @Transactional
    public List<EducationDetails> findAll(Educations edu) {
        return educationDetailsRepository.findAllByEducation(edu);
    }

    @Transactional
    public Optional<EducationDetails> findById(Long edu_detail_id) { return educationDetailsRepository.findById(edu_detail_id); }

    @Transactional
    public EducationDetails update(EducationDetails ed, EducationDetails new_ed) {
        ed.setEdu_detail_major_sort(new_ed.getEdu_detail_major_sort());
        ed.setEdu_detail_credit(Long.valueOf(new_ed.getEdu_detail_credit()));
        ed.setEdu_detail_grade(new_ed.getEdu_detail_grade());

        return educationDetailsRepository.save(ed);
    }
}
