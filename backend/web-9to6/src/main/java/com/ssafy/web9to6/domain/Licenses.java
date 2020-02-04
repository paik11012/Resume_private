package com.ssafy.web9to6.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Licenses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @Column(nullable = false)
    private String lic_name;

    @Column(nullable = false)
    private String lic_grade;

    @Column(nullable = false)
    private String lic_date;

    @Column(nullable = false)
    private String lic_num;

    @Builder
    public Licenses(Users user, String lic_name, String lic_grade, String lic_date,String lic_num) {
        this.user = user;
        this.lic_date = lic_date;
        this.lic_grade = lic_grade;
        this.lic_num = lic_num;
        this.lic_name = lic_name;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
