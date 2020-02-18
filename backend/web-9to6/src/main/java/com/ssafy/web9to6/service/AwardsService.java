package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.AwardsRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.AwardsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AwardsService {

    @Autowired
    private AwardsRepository ar;

    public List<Awards> findAll(Users user) {
        return ar.findByUser(user);
    }

    public  Awards save(AwardsResponseDto awd) {
        return ar.save(awd.toEntity());
    }

    public void delete(Long id) {
        ar.deleteById(id);
    }

    public Optional<Awards> findById(Long id) {
        return ar.findById(id);
    }

    public Awards update(Awards old, AwardsResponseDto ard) {
        System.out.println("update method" + ard.getId());
        //System.out.println("update method" + ard.toEntity2().setId(ard.getId());
        old.setAward_date(ard.getAward_date());
        old.setAward_title(ard.getAward_title());
        old.setAward_detail(ard.getAward_detail());
        old.setAward_org(ard.getAward_org());
        old.setAward_prize(ard.getAward_prize());
        return ar.save(old);
}
}
