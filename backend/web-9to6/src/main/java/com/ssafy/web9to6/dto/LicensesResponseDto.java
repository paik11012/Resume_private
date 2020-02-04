package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Licenses;
import com.ssafy.web9to6.domain.Users;
import lombok.*;

@Getter
@NoArgsConstructor

public class LicensesResponseDto {
    private Users user;
    private String lic_name;
    private String lic_grade;
    private String lic_date;
    private String lic_num;

    @Builder
    public LicensesResponseDto(Users user, String lic_name, String lic_grade, String lic_date,String lic_num) {
        this.user = user;
        this.lic_date = lic_date;
        this.lic_grade = lic_grade;
        this.lic_num = lic_num;
        this.lic_name = lic_name;
    }

    public Licenses toEntity() {
        return Licenses.builder()
                .lic_date(lic_date)
                .lic_name(lic_name)
                .lic_grade(lic_grade)
                .lic_num(lic_num)
                .user(user)
                .build();
    }
    public void setUser(Users user) {
        this.user = user;
    }

}
