package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.ExperiencesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExperiencesService {

    @Autowired
    private ExperiencesRepository er;

    public List<Experiences> findAll(Users user) {
        return er.findByUser(user);
    }

    public  Experiences save(ExperiencesResponseDto exp) {
        return er.save(exp.toEntity());
    }

    public void delete(Long id) {
        er.deleteById(id);
    }

    public Experiences findById(Long id) {
        return er.findById(id).get();
    }

    public Experiences update(Experiences old, Experiences update) {
        old.setExp_org_detail(update.getExp_org_detail());
        old.setExp_org_name(update.getExp_org_name());
        old.setExp_org_period(update.getExp_org_period());
        old.setExp_org_role(update.getExp_org_role());
        old.setExp_org_type(update.getExp_org_type());
        return er.save(old);
    }
}
