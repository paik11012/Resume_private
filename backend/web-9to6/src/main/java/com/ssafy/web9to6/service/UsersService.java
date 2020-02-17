package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.domain.UsersRepository;
import com.ssafy.web9to6.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public Users update(Users user, Users new_user) {
        if(new_user.getUser_password()!=""){
            user.setUser_password(new_user.getUser_password());
        }
        user.setUser_name(new_user.getUser_name());
        user.setUser_phone(new_user.getUser_phone());
        return usersRepository.save(user);
    }

    @Transactional
    public void delete(String user_id) {
        usersRepository.deleteById(user_id);
    }

    @Transactional
    public Users signIn(Users user) {
        String user_id = user.getUser_id();
        String user_password = user.getUser_password();
        boolean b_pass = false;
        Users find_user = null;
        Optional<Users> find = usersRepository.findById(user_id);
        if(find.isPresent()){
            find_user = find.get();
            b_pass = user_password.equals(find_user.getUser_password());
        }

        return find_user;
    }

    @Transactional
    public void deleteByAdmin(String user_id) {
        usersRepository.deleteById(user_id);
    }

    @Transactional
    public boolean checkId(String user_id) {
        Optional<Users> user = usersRepository.findById(user_id);
        if(user.isPresent()) return true;
        else return false;
    }

    @Transactional
    public Users updateAuth(String user_id) {
        Users user = usersRepository.findById(user_id).get();
        user.setUser_authority("admin");
        return usersRepository.save(user);
    }

    @Transactional
    public String getUserInfo(String access_token, String state) {
        String header = "Bearer " + access_token;
        String apiURL = "";
        String method = "";

        if(state != null) {
            apiURL = "https://openapi.naver.com/v1/nid/me";
            method = "GET";
        }
        else {
            apiURL = "https://kapi.kakao.com/v2/user/me";
            method = "POST";
        }

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200){ // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null){
                res.append(inputLine);
            }
            br.close();
            return res.toString();
        } catch (Exception e){
            System.err.println(e);
            return "Err";
        }
    }

    @Transactional
    public String setNaverUrl(String client_secret, String code, String state) {
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
        apiURL += "&client_id=" + "oEALeUqtjER7Ufo5R8f7";
        apiURL += "&client_secret=" + client_secret;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;

        return apiURL;
    }

    @Transactional
    public String setKakaoUrl(String code) {
        String apiURL = "";
        apiURL += "grant_type=authorization_code";
        apiURL += "&client_id=" + "ae103391c8a497b8820341af6a961a77";
        apiURL += "&redirect_uri=http://15.164.244.244:3000/";
        apiURL += "&code="+code;

        return apiURL;
    }
}