package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.Users;
import lombok.*;

@Getter
@NoArgsConstructor
public class AwardsResponseDto {
    private  Users user;
    private  String award_org;
    private  String award_title;
    private  String award_date;
    private  String award_prize;
    private  String award_detail;
    private String award_file;

    @Builder
    public AwardsResponseDto(Users user, String award_org,String award_title, String award_detail, String award_date,String award_prize, String award_file) {
        this.user = user;
        this.award_date = award_date;
        this.award_detail = award_detail;
        this.award_org = award_org;
        this.award_prize = award_prize;
        this.award_title = award_title;
        this.award_file = award_file;
    }


    public void setUser(Users user) {
        this.user = user;
    }

    public Awards toEntity(){
        return Awards.builder()
                .user(user)
                .award_date(award_date)
                .award_detail(award_detail)
                .award_org(award_org)
                .award_prize(award_prize)
                .award_title(award_title)
                .award_file(award_file)
                .build();
    }
}
