package com.ssafy.web9to6.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="education_details")
public class EducationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edu_detail_id;

    @Column
    private String edu_detail_major_sort; // 전공, 복수전공, 부전공

    @Column
    private String edu_detail_credit; // 이수학점

    @Column
    private Long edu_detail_grade; // 평점(학점)

    // 외래키 //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Educations education;
    // END: 외래키 //

    @Builder
    public EducationDetails(String edu_detail_major_sort, String edu_detail_credit, Long edu_detail_grade){
        this.edu_detail_major_sort = edu_detail_major_sort;
        this.edu_detail_credit = edu_detail_credit;
        this.edu_detail_grade = edu_detail_grade;
    }

    // setter //
    public void setEducation(Educations education) { this.education = education; }
    public void setEduDetail_id(Long edu_detail_id) { this.edu_detail_id = edu_detail_id; }
    // END: setter //
}
