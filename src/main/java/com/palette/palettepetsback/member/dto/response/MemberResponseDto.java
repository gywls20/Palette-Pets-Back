package com.palette.palettepetsback.member.dto.response;

import com.palette.palettepetsback.member.dto.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MemberResponseDto {

    private String memberId;
    private String name;
    private Role role;
}
