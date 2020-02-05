package com.ssafy.web9to6.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="educations")
public class Educations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long education_id;

    @Column(nullable = false)
    private String edu_school_name; // 학교명

    @Column(nullable = false)
    private String edu_school_st_date; // 재학 기간(시작)

    @Column(nullable = false)
    private String edu_school_ed_date; // 재학 기간(끝)

    @Column(nullable = false)
    private String edu_school_sort; // 학교 구분(고등학교, 대학교, 대학원, 편입)

    // 외래키 //
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;
    // END: 외래키 //

    @Builder
    public Educations(String edu_school_name, String edu_school_st_date, String edu_school_ed_date, String edu_school_sort){
        this.edu_school_name = edu_school_name;
        this.edu_school_st_date = edu_school_st_date;
        this.edu_school_ed_date = edu_school_ed_date;
        this.edu_school_sort = edu_school_sort;
    }

    // setter //
    public void setUser(Users user) { this.user = user; }
    public void setEdu_id(Long education_id) { this.education_id = education_id; }
    public void setEdu_school_name(String edu_school_name) { this.edu_school_name = edu_school_name; }
    public void setEdu_school_sort(String edu_school_sort) { this.edu_school_sort = edu_school_sort; }
    public void setEdu_school_st_date(String edu_school_st_date) { this.edu_school_st_date = edu_school_st_date; }
    public void setEdu_school_ed_date(String edu_school_st_date) { this.edu_school_ed_date = edu_school_ed_date; }
}
