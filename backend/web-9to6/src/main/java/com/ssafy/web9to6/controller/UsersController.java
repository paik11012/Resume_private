package com.ssafy.web9to6.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.*;
import com.ssafy.web9to6.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@PropertySource("classpath:config.properties")
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UsersService usersService;
    private final ResumeService resumeService;
    private final InterviewService interviewService;
    private final TagService tagService;

    @Autowired
    private JwtService jwtService;

    @Value("${client_secret}")
    private String client_secret;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfService pdfService;

    @ApiOperation("회원 이메일(ID) 중복체크")
    @PostMapping("/users/checkId")
    public boolean userCheckId(@RequestBody UsersResponseDto requestDto){
        String user_id = requestDto.getUser_id();
        return usersService.checkId(user_id);
    }

    @ApiOperation("비밀번호 체크")
    @PostMapping("/users/checkPass")
    public boolean userCheckPass(@RequestBody UsersResponseDto responseDto){
        String user_id = responseDto.getUser_id();
        String user_pass = responseDto.getUser_password();
        Users user = usersService.findById(user_id);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(user.getUser_password(), user_pass)) return true;
        else return false;
    }

    @ApiOperation("회원 등록")
    @PostMapping("/users/signup")
    public Users userSignUp(@RequestBody UsersResponseDto requestDto) throws Exception {
        // DB에 회원 등록 //
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setUser_password(passwordEncoder.encode(requestDto.getUser_password()));
        Users user = requestDto.toEntity();
        user.setUser_authority("user");
        user = usersService.save(user);
        // END: DB에 회원 등록 //

        // Resume, Interview 샘플 데이터 생성 //
        // Resume //
        ResumeResponseDto resumeDto = ResumeResponseDto.builder()
                .resume_company("냥회사")
                .resume_task("SW개발")
                .resume_date("2019 하반기")
                .resume_question("OO기업에 지원한 동기를 작성해주세요.")
                .resume_answer("돈 벌고 싶어냥 / 팁: 이곳에 기준 글자 수, 바이트를 써 놓으면 검색으로 쉽게 찾을 수 있다냥")
                .build();
        resumeDto.setUser(user);
        Resume resume = resumeService.save(resumeDto);

        TagResponseDto tagDto = TagResponseDto.builder()
                .tag_name("지원동기")
                .build();
        tagDto.setResume(resume);
        tagService.save(tagDto);
        // END: Resume //

        // Interview //
        InterviewResponseDto interviewDto = InterviewResponseDto.builder()
                .interview_company("냥회사")
                .interview_task("SW 개발")
                .interview_date("2019 하반기")
                .interview_answer("냥냥")
                .interview_question("본인의 강점은 무엇인가요?")
                .interview_memo("코딩 잘 한다고 할걸,,")
                .build();
        interviewDto.setUser(user);
        interviewService.save(interviewDto);
        // END: Interview //
        // END: Resume, Interview 샘플 데이터 생성 //
        return user;
    }

    @ApiOperation("회원 로그인")
    @PostMapping("/users/signin")
    public ResponseEntity<Map<String, Object>> userSignIn(HttpServletResponse response, HttpServletRequest request, @RequestBody UsersResponseDto requestDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users users = requestDto.toEntity();
        Users res = usersService.signIn(users);
        System.out.println("iam id" + res.getUser_id());
        Map<String, Object> resultmap = new HashMap<>();
        HttpStatus status = null;
        if(res.getUser_id().equals(users.getUser_id())){
            if(passwordEncoder.matches(users.getUser_password(),res.getUser_password())) {
                String token = jwtService.create(res);
                response = jwtService.setHeaders(response, token);

                resultmap.put("data", res);
                resultmap.put("status", true);
                resultmap.put("jwt-auth-token", token);
                status = HttpStatus.ACCEPTED;
            } else throw
                    new Exception("비밀번호 오류");

            //return res;
        }
        //return res;
        return new  ResponseEntity<Map<String, Object>>(resultmap, status);
    }

    @ApiOperation("네이버/카카오 로그인")
    @PostMapping("/users/loginSocial")
    public ResponseEntity<Map<String, Object>> userSigninNaver(HttpServletResponse response, @RequestBody SocialResponseDto socialResponseDto) throws Exception{
        Map<String, Object> resultmap = new HashMap<>();
        HttpStatus status = null;

        String code = socialResponseDto.getNcode();
        String state = socialResponseDto.getNstate();

        // client_id, client_sercret, code, state를 가지고 소셜에 token 요청
        String apiURL;
        HttpURLConnection con = null;
        if(state!=null){ // naver
            apiURL = usersService.setNaverUrl(client_secret, code, state);

            URL url = new URL(apiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        }
        else { // kakko
            apiURL = "https://kauth.kakao.com/oauth/token";

            URL url = new URL(apiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(usersService.setKakaoUrl(code));
            bw.flush();
        }

        String token = "";
        try{
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200){ br = new BufferedReader(new InputStreamReader(con.getInputStream())); }
            else { br = new BufferedReader(new InputStreamReader(con.getErrorStream())); }

            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null){ res.append(inputLine); }
            br.close();

            if(responseCode==200){ // 성공적으로 토큰을 가져오면
                int id;
                String name, email, tmp;

                JsonParser parser = new JsonParser();
                JsonElement accessElement = parser.parse(res.toString());
                token = accessElement.getAsJsonObject().get("access_token").getAsString();

                tmp = usersService.getUserInfo(token, state);
                JsonElement userInfoElement = parser.parse(tmp);
                if(state!=null){ // naver
                    name = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
                    email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
                }
                else{ // kakao
                    name = userInfoElement.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
                    email = userInfoElement.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
                }

                // 기존 회원인지 확인
                boolean b_find = usersService.checkId(email);
                Users user;
                if(!b_find){ // 없으면 Users DB에 등록
                    user = usersService.save(Users.builder()
                            .user_id(email)
                            .user_name(name)
                            .user_password("")
                            .user_authority("user")
                            .build());
                }
                else{ user = usersService.findById(email); }

                token = jwtService.create(user);
                response = jwtService.setHeaders(response, token);

                resultmap.put("data", user);
                resultmap.put("status", true);
                resultmap.put("jwt-auth-token", token);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<Map<String, Object>>(resultmap, status);
    }

    @ApiOperation("모든 회원 조회")
    @GetMapping("/users/findAll")
    public List<Users> userFindAll(HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);
        if(user.getUser_authority().equals("admin")) return usersService.findAll();

        return new LinkedList<>();
    }

    @ApiOperation("회원 조회")
    @GetMapping("/users/findOne/{user_id}")
    public Users userFindOne(@PathVariable String user_id){
        return usersService.findById(user_id);
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/users/update")
    public Users userUpdate(HttpServletRequest request, @RequestBody UsersResponseDto requestDto){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);

        if(requestDto.getUser_password()!=""){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            requestDto.setUser_password(passwordEncoder.encode(requestDto.getUser_password()));
        }

        return usersService.update(user, requestDto.toEntity());
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping("/users/delete")
    public void userDelete(HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        usersService.delete(user_id);
    }

    @ApiOperation("회원 탈퇴 by admin")
    @DeleteMapping("/users/deleteByAdmin/{user_id}")
    public void userDeleteByAdmin(HttpServletRequest request, @PathVariable String user_id){
        String admin_id = request.getHeader("user_id");
        Users admin = usersService.findById(admin_id);
       // if(admin.getUser_authority().equals("admin")){
            usersService.delete(user_id);
        //}
    }

    @ApiOperation("임시 비밀번호 메일 발송")
    @GetMapping("/users/sendtmp/{user_id}")
    public void send(@PathVariable String user_id) throws Exception {
        emailService.sendSimpleMessage( usersService.findById(user_id));
    }

    @ApiOperation("회원가입 시 인증번호 메일 발송")
    @GetMapping("/users/sendmail/{user_id}")
    public String sendAuthencationMail(@PathVariable String user_id) throws Exception {
       return emailService.sendAuthencationMail(user_id);
    }

    @ApiOperation("회원 관리자 권한 부여")
    @PutMapping("/users/changeAuth/{user_id}")
    public Users userChangeAuth(@PathVariable String user_id){
        return usersService.updateAuth(user_id);
    }

   
    @ApiOperation("회원 프로필 사진 업로드")
    @PostMapping("/users/uploadProfileImg")
    public Users userUploadProfileImg(HttpServletRequest request, @RequestBody UsersResponseDto requestDto){
        String user_id = request.getHeader("user_id");
        Users user = usersService.findById(user_id);
        user.setUser_profile_img(requestDto.getUser_profile_img());
        return usersService.save(user);

    }
}