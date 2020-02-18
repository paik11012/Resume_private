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
    private String edu_detail_major; // 학과명

    @Column
    private Long edu_detail_credit; // 이수학점

    @Column
    private Double edu_detail_grade; // 평점(학점)

    @Column
    private String edu_detail_grade_img; // 성적표 이미지

    // 외래키 //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Educations education;
    // END: 외래키 //

    @Builder
    public EducationDetails(String edu_detail_major_sort, Long edu_detail_credit, Double edu_detail_grade, String edu_detail_major, String edu_detail_grade_img){
        this.edu_detail_major_sort = edu_detail_major_sort;
        this.edu_detail_major = edu_detail_major;
        this.edu_detail_credit = edu_detail_credit;
        this.edu_detail_grade = edu_detail_grade;
        this.edu_detail_grade_img = edu_detail_grade_img;
    }

    // setter //
    public void setEducation(Educations education) { this.education = education; }
    public void setEduDetail_id(Long edu_detail_id) { this.edu_detail_id = edu_detail_id; }
    public void setEdu_detail_major_sort(String edu_detail_major_sort) { this.edu_detail_major_sort = edu_detail_major_sort; }
    public void setEdu_detail_major(String edu_detail_major) { this.edu_detail_major = edu_detail_major; }
    public void setEdu_detail_credit(Long edu_detail_credit) { this.edu_detail_credit = edu_detail_credit; }
    public void setEdu_detail_grade(Double edu_detail_grade) { this.edu_detail_grade = edu_detail_grade; }
    public void setEdu_detail_grade_img(String edu_detail_grade_img) { this.edu_detail_grade_img = edu_detail_grade_img; }
    // END: setter //
}
