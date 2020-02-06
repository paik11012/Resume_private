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
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @Column(nullable = true)
    private String interview_company;

    @Column(nullable = true)
    private String interview_task;

    @Column(nullable = true)
    private String interview_date;

    @Column(nullable = true)
    private String interview_question;

    @Column(nullable = true)
    private String interview_answer;

    @Column
    private String interview_memo;

    @Builder
    public Interview(Users user, String interview_memo,String interview_company, String interview_task, String interview_date, String interview_question, String interview_answer) {
        this.user = user;
        this.interview_answer = interview_answer;
        this.interview_company = interview_company;
        this.interview_date = interview_date;
        this.interview_question = interview_question;
        this.interview_task = interview_task;
        this.interview_memo = interview_memo;
    }
}
