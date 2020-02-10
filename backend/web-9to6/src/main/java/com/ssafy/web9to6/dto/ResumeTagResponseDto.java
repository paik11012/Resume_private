package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Tag;
import com.ssafy.web9to6.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ResumeTagResponseDto {
    private List<String> tag_name;
    private Resume resume;

    @Builder
    public ResumeTagResponseDto(Resume resume, List<String> tag_name) {
        this.resume = resume;
        this.tag_name = tag_name;
    }
}
