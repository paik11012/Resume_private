package com.ssafy.web9to6.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareersRepository extends JpaRepository<Careers, Long> {
    public Careers findByUser(Users user);
}
