package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Interview;
import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InterviewResponseDto {
    private Users user;
    private String interview_company;
    private String interview_task;
    private String interview_date;
    private String interview_question;
    private String interview_answer;
    private String interview_memo;
    private Long id;

    @Builder
    public InterviewResponseDto(Users user,String interview_memo, String interview_company, String interview_task, String interview_date, String interview_question, String interview_answer) {
        this.user = user;
        this.interview_answer = interview_answer;
        this.interview_company = interview_company;
        this.interview_date = interview_date;
        this.interview_question = interview_question;
        this.interview_task = interview_task;
        this.interview_memo = interview_memo;
    }

    public Interview toEntity() {
        return Interview.builder()
                .interview_answer(interview_answer)
                .interview_company(interview_company)
                .interview_question(interview_question)
                .interview_date(interview_date)
                .interview_task(interview_task)
                .interview_memo(interview_memo)
                .user(user)
                .build();

    }

    public void setUser(Users user) {
        this.user = user;
    }

}
