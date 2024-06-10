package com.palette.palettepetsback.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MemberRequest {
    @Getter @Setter
    @NoArgsConstructor
    public static class Password{
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@!%*#?&]).{8,16}$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 포함한 8~16자리여야 합니다.")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        private String password;
        @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
        private String checkPassword;
    }@Getter @Setter
    @NoArgsConstructor
    public static class Email{
        @NotBlank(message = "이메일을 입력해 주세요.")
        private String email;
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class Nickname {
        @NotBlank(message = "닉네임을 입력해 주세요.")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        private String nickName;
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class Address {
        @NotBlank(message = "성함을 입력해 주세요.")
        private String name;
        @NotBlank(message = "휴대폰번호를 입력해 주세요.")
        private String phone;
        @NotBlank(message = "주소를 입력해 주세요.")
        private String address;

    }
    @Getter @Setter
    @NoArgsConstructor
    public static class Image {
        @NotBlank(message = "이미지를 입력해 주세요.")
        private String image;
    }
    @Getter @Setter
    @NoArgsConstructor
    public static class BirthGender {
        @NotBlank(message = "생일을 입력해 주세요.")
        private String birth;
        @NotBlank(message = "성별을 입력해 주세요.")
        private String gender;

    }


}
