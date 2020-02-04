package com.ssafy.web9to6.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name="careers")
public class Careers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long career_id;

    @Column
    private String career_myPic;

    // 병역 관련 //
    @Column
    private String military_sort; // 구분(육군, 공군..)

    @Column
    private String military_st_date; // 복무 시작 날짜

    @Column
    private String military_ed_date; // 복무 완료 날짜

    @Column
    private String military_class; // 계급
    // END: 병역 관련 //

    // 외래키 //
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

//    @OneToMany(mappedBy = "career", orphanRemoval = true, cascade = CascadeType.REMOVE)
//    private List<Educations> educations;
    // END: 외래키 //


    @Builder
    public Careers(String career_myPic, String military_sort, String military_st_date, String military_ed_date, String military_class){
        this.career_myPic = career_myPic;
        this.military_sort = military_sort;
        this.military_st_date = military_st_date;
        this.military_ed_date = military_ed_date;
        this.military_class = military_class;
    }

    // setter //
    public void setCareer_id(Long career_id) {
        this.career_id = career_id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

//    public void addEducations(Educations education) {
//        this.educations.add(education);
//    }

    // setter 역할
}
