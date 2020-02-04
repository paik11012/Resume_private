package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Educations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

@Getter
@NoArgsConstructor
public class EducationsResponseDto {
    private String edu_school_name;
    private String edu_school_st_date;
    private String edu_school_ed_date;
    private String edu_school_sort;

    @Builder
    public EducationsResponseDto(String edu_school_name, String edu_school_st_date, String edu_school_ed_date, String edu_school_sort){
        this.edu_school_name = edu_school_name;
        this.edu_school_st_date = edu_school_st_date;
        this.edu_school_ed_date = edu_school_ed_date;
        this.edu_school_sort = edu_school_sort;
    }

    public Educations toEntity(){
        return Educations.builder()
                .edu_school_name(edu_school_name)
                .edu_school_st_date(edu_school_st_date)
                .edu_school_ed_date(edu_school_ed_date)
                .edu_school_sort(edu_school_sort)
                .build();
    }
}
