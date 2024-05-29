package com.palette.palettepetsback.member.dto;

public interface OAuth2Response {
    //제공자 (Ex. naver, google, ...)
    String getProvider();
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();

    String getName();//사용자 실명
    String getEmail();
    String getNickname();
    String getProfile_image();
    String getGender();
    String getBirthday();
    String getPhone_number();
}
