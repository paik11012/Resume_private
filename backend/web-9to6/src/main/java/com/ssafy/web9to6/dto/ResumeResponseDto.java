package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResumeResponseDto {
    private Users user;
    private String resume_company;
    private String resume_task;
    private String resume_date;
    private String resume_question;
    private String resume_answer;
    private Long id;

    @Builder
    public ResumeResponseDto(String resume_company,String resume_task,String resume_date,String resume_question,String resume_answer, Users user) {
        this.resume_answer = resume_answer;
        this.resume_company = resume_company;
        this.resume_date = resume_date;
        this.resume_question = resume_question;
        this.resume_task = resume_task;
        this.user = user;
    }

    public Resume toEntity() {
        return Resume.builder()
                .resume_answer(resume_answer)
                .resume_company(resume_company)
                .resume_question(resume_question)
                .resume_task(resume_task)
                .resume_date(resume_date)
                .user(user)
                .build();

    }

    public void setUser(Users user) {
        this.user = user;
    }

}
