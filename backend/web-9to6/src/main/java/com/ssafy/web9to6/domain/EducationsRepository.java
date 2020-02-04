package com.ssafy.web9to6.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationsRepository extends JpaRepository<Educations, Long> {
    List<Educations> findByUser(Users user);
//    @Query(value = "select edu from educations edu order by edu.edu_school_ed_date asc" , nativeQuery = false)
//    public List<Educations> findAllOrderByEdDate();
}
