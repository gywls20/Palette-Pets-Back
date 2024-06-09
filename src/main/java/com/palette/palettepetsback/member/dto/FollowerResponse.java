package com.palette.palettepetsback.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public class FollowerResponse {

    private String nickname;
    private String profileImage;

    public FollowerResponse(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }



}
