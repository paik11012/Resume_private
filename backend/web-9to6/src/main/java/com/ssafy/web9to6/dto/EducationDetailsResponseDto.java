package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.EducationDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EducationDetailsResponseDto {
    private String edu_detail_major_sort;
    private String edu_detail_credit;
    private Long edu_detail_grade;

    @Builder
    public EducationDetailsResponseDto(String edu_detail_major_sort, String edu_detail_credit, Long edu_detail_grade){
        this.edu_detail_major_sort = edu_detail_major_sort;
        this.edu_detail_credit = edu_detail_credit;
        this.edu_detail_grade = edu_detail_grade;
    }

    public EducationDetails toEntity(){
        return EducationDetails.builder()
                .edu_detail_major_sort(edu_detail_major_sort)
                .edu_detail_credit(edu_detail_credit)
                .edu_detail_grade(edu_detail_grade)
                .build();
    }
}
