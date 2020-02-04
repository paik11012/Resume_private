package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.AwardsRepository;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.AwardsResponseDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
