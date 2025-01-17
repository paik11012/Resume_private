package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Users;
import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

@Getter
@Setter
@NoArgsConstructor
public class UsersResponseDto {
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_phone;
    private String user_authority;
    private String user_profile_img;

    @Builder
    public UsersResponseDto(String user_id, String user_password, String user_name, String user_phone, String user_authority, String user_profile_img){
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_authority = user_authority;
        this.user_profile_img = user_profile_img;
    }

    public UsersResponseDto(String user_password, String user_name, String user_phone, String user_profile_img){
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_profile_img = user_profile_img;
    }
    
    public Users toEntity() {
        return Users.builder()
                .user_id(user_id)
                .user_password(user_password)
                .user_name(user_name)
                .user_phone(user_phone)
                .user_authority(user_authority)
                .user_profile_img(user_profile_img)
                .build();
    }
}
