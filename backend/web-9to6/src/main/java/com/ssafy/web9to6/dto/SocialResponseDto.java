package com.ssafy.web9to6.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SocialResponseDto {
    private String ncode;
    private String nstate;

    @Builder
    public SocialResponseDto(String ncode, String nstate){
        this.ncode = ncode;
        this.nstate = nstate;
    }
}