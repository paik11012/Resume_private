package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.*;
import com.ssafy.web9to6.dto.LicensesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LicensesService {

    @Autowired
    private LicensesRepository lr;

    public List<Licenses> findAll(Users user) {
        return lr.findByUser(user);
    }

    public  Licenses save(LicensesResponseDto lic) {
        return lr.save(lic.toEntity());
    }

    public void delete(Long id) {
        lr.deleteById(id);
    }
}
