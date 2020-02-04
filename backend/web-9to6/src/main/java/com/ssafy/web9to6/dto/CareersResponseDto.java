package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Careers;
import com.ssafy.web9to6.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CareersResponseDto {
    private String career_myPic;
    private String military_sort;
    private String military_st_date;
    private String military_ed_date;
    private String military_class;

    @Builder
    public CareersResponseDto(String career_myPic, String military_sort, String military_st_date, String military_ed_date, String military_class){
        this.career_myPic = career_myPic;
        this.military_sort = military_sort;
        this.military_st_date = military_st_date;
        this.military_ed_date = military_ed_date;
        this.military_class = military_class;
    }

    public Careers toEntity(){
        return Careers.builder()
                .career_myPic(career_myPic)
                .military_sort(military_sort)
                .military_st_date(military_st_date)
                .military_ed_date(military_ed_date)
                .military_class(military_class)
                .build();
    }
}
