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
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @Column(nullable = true)
    private String resume_company;

    @Column(nullable = true)
    private String resume_task;

    @Column(nullable = true)
    private String resume_date;

    @Column(nullable = true,length=1000)
    private String resume_question;

    @Column(nullable = true,length=3000)
    private String resume_answer;

    @Builder
    public Resume(Users user,String resume_company,String resume_task,String resume_date,String resume_question,String resume_answer) {
        this.user = user;
        this.resume_answer = resume_answer;
        this.resume_company = resume_company;
        this.resume_date = resume_date;
        this.resume_question = resume_question;
        this.resume_task = resume_task;
    }


}
