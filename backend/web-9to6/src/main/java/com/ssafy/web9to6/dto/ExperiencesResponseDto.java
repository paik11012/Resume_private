package com.ssafy.web9to6.dto;

import com.ssafy.web9to6.domain.Awards;
import com.ssafy.web9to6.domain.Experiences;
import com.ssafy.web9to6.domain.Users;
import lombok.*;


@Getter
@NoArgsConstructor
public class ExperiencesResponseDto {
    private Long id;
    private Users user;
    private String exp_org_name;
    private String exp_org_period;
    private String exp_org_type;
    private String exp_org_role;
    private String exp_org_detail;

    @Builder  // 빌더 패턴 클래스 생성. 생성자 상단에 선언 시, 생성자에 포함된 필드만 빌더에 포함
    public ExperiencesResponseDto(Users user, String exp_org_name, String exp_org_period, String exp_org_type, String exp_org_role, String exp_org_detail) {
        this.user = user;
        this.exp_org_detail = exp_org_detail;
        this.exp_org_name = exp_org_name;
        this.exp_org_period = exp_org_period;
        this.exp_org_role = exp_org_role;
        this.exp_org_type = exp_org_type;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Experiences toEntity() {
        return Experiences.builder()
                .exp_org_name(exp_org_name)
                .exp_org_period(exp_org_period)
                .exp_org_detail(exp_org_detail)
                .exp_org_role(exp_org_role)
                .exp_org_type(exp_org_type)
                .user(user)
                .build();
    }
}
