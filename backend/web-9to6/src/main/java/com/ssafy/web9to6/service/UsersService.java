package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Transactional
    public Users findById(String user_id) {
        return usersRepository.findById(user_id).get();
    }

    @Transactional
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users update(String user_id, Users new_user) {
        new_user.setUser_id(user_id);
        return usersRepository.save(new_user);
    }

    @Transactional
    public void delete(String user_id) {
        usersRepository.deleteById(user_id);
    }

    @Transactional
    public Boolean signIn(Users user) {
        String user_id = user.getUser_id();
        String user_password = user.getUser_password();
        boolean b_pass = false;

        Optional<Users> find = usersRepository.findById(user_id);
        if(find.isPresent()){
            Users find_user = find.get();
            b_pass = user_password.equals(find_user.getUser_password());
        }

        return b_pass;
    }

    @Transactional
    public void deleteByAdmin(String user_id) {
        usersRepository.deleteById(user_id);
    }
}