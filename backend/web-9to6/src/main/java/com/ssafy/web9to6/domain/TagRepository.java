package com.ssafy.web9to6.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
//    List<Tag> findByTagName(String tag_name);
    public List<Tag> findByResume(Resume resume);
}
