package com.ssafy.web9to6.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.web9to6.domain.Users;
import com.ssafy.web9to6.dto.NaverResponseDto;
import com.ssafy.web9to6.dto.UsersResponseDto;
import com.ssafy.web9to6.service.EmailService;
import com.ssafy.web9to6.service.JwtService;
import com.ssafy.web9to6.service.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@PropertySource("classpath:config.properties")
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UsersService usersService;

    @Autowired
    private JwtService jwtService;

    @Value("${client_secret}")
    private String client_secret;

    @Autowired
    private EmailService emailService;

    // 수정수정
    @ApiOperation("회원 이메일(ID) 중복체크")
    @PostMapping("/users/checkId")
    public boolean userCheckId(@RequestBody UsersResponseDto requestDto){
        String user_id = requestDto.getUser_id();
        return usersService.checkId(user_id);
    }

    @ApiOperation("회원 등록")
    @PostMapping("/users/signup")
    public Users userSignUp(@RequestBody UsersResponseDto requestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setUser_password(passwordEncoder.encode(requestDto.getUser_password()));
        Users user = requestDto.toEntity();
        user.setUser_authority("user");
        return usersService.save(user);
    }

    @ApiOperation("회원 로그인")
    @PostMapping("/users/signin")
    public ResponseEntity<Map<String, Object>> userSignIn(HttpServletResponse response, HttpServletRequest request, @RequestBody UsersResponseDto requestDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users users = requestDto.toEntity();
        Users res = usersService.signIn(users);
        Map<String, Object> resultmap = new HashMap<>();
        HttpStatus status = null;
        if(res.getUser_id().equals(users.getUser_id())){
            if(passwordEncoder.matches(users.getUser_password(),res.getUser_password())) {
                String token = jwtService.create(res);
                response.setHeader("jwt-auth-token", token);
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Headers","Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
                response.setHeader("Access-Control-Max-Age","3600 ");
                response.setHeader("Access-Control-Allow-Methods","*");
                response.setHeader("Access-Control-Expose-Headers","jwt-auth-token");

                resultmap.put("data", res);
                resultmap.put("status", true);
                resultmap.put("jwt-auth-token", token);
                status = HttpStatus.ACCEPTED;
            } else throw new Exception("비밀번호 오류");

            //return res;
        }
        //return res;
        return new  ResponseEntity<Map<String, Object>>(resultmap, status);
    }

    @ApiOperation("네이버 로그인")
    @PostMapping("/users/loginNaver")
    public ResponseEntity<Map<String, Object>> userSigninNaver(HttpServletResponse response, @RequestBody NaverResponseDto naverResponseDto) throws Exception{
        Map<String, Object> resultmap = new HashMap<>();
        HttpStatus status = null;

        // client_id, client_sercret, code, state를 가지고 네이버에 token 요청
        String cline_id = "oEALeUqtjER7Ufo5R8f7";
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
        apiURL += "&client_id=" + cline_id;
        apiURL += "&client_secret=" + client_secret;
        apiURL += "&code=" + naverResponseDto.getNcode();
        apiURL += "&state=" + naverResponseDto.getNstate();

        String token = "";
        try{
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200){ // 정상호출
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

            if(responseCode==200){ // 성공적으로 토큰을 가져오면
                int id;
                String name, email, tmp;

                JsonParser parser = new JsonParser();
                JsonElement accessElement = parser.parse(res.toString());
                token = accessElement.getAsJsonObject().get("access_token").getAsString();

                tmp = usersService.getUserInfo(token);
                JsonElement userInfoElement = parser.parse(tmp);
                id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsInt();
                name = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
                email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();

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
                else{
                    user = usersService.findById(email);
                }

                token = jwtService.create(user);
                response.setHeader("jwt-auth-token", token);
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Headers","Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
                response.setHeader("Access-Control-Max-Age","3600 ");
                response.setHeader("Access-Control-Allow-Methods","*");
                response.setHeader("Access-Control-Expose-Headers","jwt-auth-token");

                resultmap.put("data", user);
                resultmap.put("status", true);
                resultmap.put("jwt-auth-token", token);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e){
            System.out.println(e);
        }

//        return "redirect:http://localhost:8081/home";
        return new ResponseEntity<Map<String, Object>>(resultmap, status);
    }

    @ApiOperation("회원 로그아웃")
    @GetMapping("/users/signout")
    public String userSignOut(HttpServletRequest request){
        return "false";
    }

    @ApiOperation("모든 회원 조회")
    @GetMapping("/users/findAll")
    public List<Users> userFindAll(HttpServletRequest request){
        String user_id = "admin@ssafy.com";
        if(user_id.equals("admin@ssafy.com")){
            return usersService.findAll();
        }
        return null;
    }

    @ApiOperation("회원 조회")
    @GetMapping("/users/findOne/{user_id}")
    public Users userFindOne(@PathVariable String user_id){
        return usersService.findById(user_id);
    }

    @ApiOperation("회원 정보 수정")
    @PutMapping("/users/update")
    public Users userUpdate(HttpServletRequest request, @RequestBody UsersResponseDto requestDto){
        String user_id = "ds@ssafy.com";
        Users user = usersService.findById(user_id);
        return usersService.update(user, requestDto.toEntity());
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping("/users/delete")
    public void userDelete(HttpServletRequest request){
        String user_id = "test";
        usersService.delete(user_id);
    }

    @ApiOperation("회원 hello by admin")
    @DeleteMapping("/users/deleteByAdmin/{user_id}")
    public void userDeleteByAdmin(HttpServletRequest request, @PathVariable String user_id){
        String admin_id = "ds@ssafy.com";
        Users admin = usersService.findById(admin_id);
        if(admin.getUser_authority().equals("admin")){
            usersService.delete(user_id);
        }
    }

    @ApiOperation("메일 보내기")
    @GetMapping("/users/sendmail")
    public void send(){
//        emailService.sendSimpleMessage("minju11012@gmail.com","me","dd");
        emailService.sendSimpleMessage("ojinga0519@gmail.com","me","dd");
    }

    @ApiOperation("회원 관리자 권한 부여")
    @PutMapping("/users/changeAuth/{user_id}")
    public Users userChangeAuth(@PathVariable String user_id){
        return usersService.updateAuth(user_id);
    }
}