package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.EducationDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationDetailsResponseDto {
    private String edu_detail_id;
    private String edu_detail_major_sort;
    private Long edu_detail_credit;
    private Double edu_detail_grade;
    private String edu_detail_grade_img;

    @Builder
    public EducationDetailsResponseDto(String edu_detail_id, String edu_detail_major_sort, Long edu_detail_credit, Double edu_detail_grade, String edu_detail_grade_img){
        this.edu_detail_id = edu_detail_id;
        this.edu_detail_major_sort = edu_detail_major_sort;
        this.edu_detail_credit = edu_detail_credit;
        this.edu_detail_grade = edu_detail_grade;
        this.edu_detail_grade_img = edu_detail_grade_img;
    }

    public EducationDetails toEntity(){
        EducationDetails edu_det =  EducationDetails.builder()
                .edu_detail_major_sort(edu_detail_major_sort)
                .edu_detail_credit(edu_detail_credit)
                .edu_detail_grade(edu_detail_grade)
                .edu_detail_grade_img(edu_detail_grade_img)
                .build();
        edu_det.setEduDetail_id(Long.valueOf(edu_detail_id));
        return edu_det;
    }
}
