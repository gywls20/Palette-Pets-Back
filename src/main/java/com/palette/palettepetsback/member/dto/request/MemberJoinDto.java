package com.palette.palettepetsback.member.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberJoinDto {

    private String memberId;
    private String name;
    private String password;
}
