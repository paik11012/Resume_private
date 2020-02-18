package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class EducationsService {
    private final EducationsRepository educationsRepository;
    private final UsersRepository usersRepository;
    private final CareersRepository careersRepository;

    @Transactional
    public Educations save(Educations education) {
        return educationsRepository.save(education);
    }

    @Transactional
    public List<Educations> findAll(Users user) {
        List<Educations> educationsList = educationsRepository.findByUser(user);
        return educationsList;
    }

    @Transactional
    public void deleteOne(Long edu_id) {
        educationsRepository.deleteById(edu_id);
    }

    @Transactional
    public List<Educations> findByUser(Users user) {
        return educationsRepository.findByUser(user);
    }

    @Transactional
    public Optional<Educations> findById(Long edu_id) {
        return educationsRepository.findById(edu_id);
    }

    @Transactional
    public Educations update(Educations edu, Educations new_edu) {
        edu.setEdu_school_name(new_edu.getEdu_school_name());
        edu.setEdu_school_sort(new_edu.getEdu_school_sort());
        edu.setEdu_school_st_date(new_edu.getEdu_school_st_date());
        edu.setEdu_school_ed_date(new_edu.getEdu_school_ed_date());

        return educationsRepository.save(edu);
    }
}