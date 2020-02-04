package com.ssafy.web9to6.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationFullResponseDto {
    private Long edu_id;
    private Long edu_detail_id;

    private String edu_school_sort;
    private String edu_school_name;
    private String edu_school_st_date;
    private String edu_school_ed_date;

    private String edu_detail_major_sort;
    private String edu_detail_credit;
    private Long edu_detail_grade;

    @Builder
    public EducationFullResponseDto(Long edu_id, Long edu_detail_id, String edu_school_sort, String  edu_school_name, String edu_school_st_date, String edu_school_ed_date, String edu_detail_major_sort, String edu_detail_credit, Long edu_detail_grade){
        this.edu_id = edu_id;
        this.edu_detail_id = edu_detail_id;

        this.edu_school_sort = edu_school_sort;
        this.edu_school_name = edu_school_name;
        this.edu_school_st_date = edu_school_st_date;
        this.edu_school_ed_date = edu_school_ed_date;

        this.edu_detail_major_sort = edu_detail_major_sort;
        this.edu_detail_credit = edu_detail_credit;
        this.edu_detail_grade = edu_detail_grade;
    }

//    public EducationDetails toEntity(){
//        return EducationDetails.builder()
//                .edu_detail_major_sort(edu_detail_major_sort)
//                .edu_detail_credit(edu_detail_credit)
//                .edu_detail_grade(edu_detail_grade)
//                .build();
//    }
}