package com.ssafy.web9to6.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Long> {
    List<EducationDetails> findAllByEducation(Educations educations);
}
