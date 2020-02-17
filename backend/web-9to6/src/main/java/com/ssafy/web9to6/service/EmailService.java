package com.ssafy.web9to6.service;

import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.UsersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UsersService usersService;

    public void sendSimpleMessage(Users user) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SimpleMailMessage message = new SimpleMailMessage();
        String temp_pwd =  getRamdomPassword();
        System.out.println(temp_pwd);
        message.setTo(user.getUser_id());
        message.setSubject("[취뽀냥이]" + user.getUser_name() + " 임시 비밀번호 발송");
        message.setText("안녕하세요 저희는 취뽀냥이 웹페이지 입니다 \n " +
                "회원님의 임시비밀번호는 [" + temp_pwd + "] 입니다. \n 저희 사이트 많은 이용바랄게요♥♡♥♡");
       try {
           emailSender.send(message);
           UsersResponseDto urd = new UsersResponseDto(user.getUser_password(),user.getUser_name(),user.getUser_phone(),user.getUser_profile_img());
           urd.setUser_password(passwordEncoder.encode(temp_pwd));
           usersService.update(user,  urd.toEntity());
       } catch (Exception e) {
           throw new Exception("임시 비밀번호 발송 에러");
       }

    }

    public static String getRamdomPassword() {
        int len = 7;
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; int idx = 0;
        StringBuffer sb = new StringBuffer();
        System.out.println("charSet.length :::: "+charSet.length);
        for (int i = 0; i < len; i++) {
            idx = (int) (charSet.length * Math.random());
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

    public String sendAuthencationMail(String id) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        String temp_pwd =  getRamdomPassword();
        System.out.println(temp_pwd);
        message.setTo(id);
        message.setSubject("[취뽀냥이]회원가입 인증번호 발송");
        message.setText("안녕하세요 저희는 취뽀냥이 웹페이지 입니다 \n " +
                "회원님의 인증번호는 [" + temp_pwd + "] 입니다. \n 회원가입을 축하드립니다. ♡ \n " +
                "          ฅ^._.^ฅ       ");
        try {
            emailSender.send(message);
            return temp_pwd;
        } catch (Exception e) {
            throw new Exception("인증 번호 발송 에러");
        }

    }
}
