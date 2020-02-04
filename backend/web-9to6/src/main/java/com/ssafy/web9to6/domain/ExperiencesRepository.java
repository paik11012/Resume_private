package com.ssafy.web9to6.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperiencesRepository extends JpaRepository<Experiences, Long> {
    public List<Experiences> findByUser(Users user);
}
