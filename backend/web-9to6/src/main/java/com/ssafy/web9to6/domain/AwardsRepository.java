package com.ssafy.web9to6.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface AwardsRepository extends JpaRepository<Awards, Long> {
    public List<Awards> findByUser(Users user);
}
