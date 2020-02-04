package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Resume;
import com.ssafy.web9to6.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TagResponseDto {
    private Resume resume;
    private String tag_name;

    @Builder
    public TagResponseDto(Resume resume, String tag_name) {
        this.resume = resume;
        this.tag_name = tag_name;
    }

    public Tag toEntity() {
        return Tag.builder()
                .resume(resume)
                .tag_name(tag_name)
                .build();
    }
}
