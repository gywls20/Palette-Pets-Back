package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.member.dto.*;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Member existData = userRepository.findByPassword(username);

        if (existData == null) {

            Member member = new Member();
            member.setPassword(username);
            member.setEmail(oAuth2Response.getEmail());
            member.setMemberName(oAuth2Response.getName());
            member.setMemberNickname(oAuth2Response.getNickname());
            member.setMemberBirth(oAuth2Response.getBirthday());
            member.setMemberGender(oAuth2Response.getGender());
            member.setMemberPhone(oAuth2Response.getPhone_number());

            userRepository.save(member);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("USER");

            return new CustomOAuth2User(userDTO);
        }
        else {
            //데이터 업데이트
            existData.setEmail(oAuth2Response.getEmail());
            existData.setMemberName(oAuth2Response.getName());
            existData.setMemberNickname(oAuth2Response.getNickname());
            existData.setMemberBirth(oAuth2Response.getBirthday());
            existData.setMemberGender(oAuth2Response.getGender());
            existData.setMemberPhone(oAuth2Response.getPhone_number());
            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getPassword());
            userDTO.setName(oAuth2Response.getName());

            return new CustomOAuth2User(userDTO);
        }
    }
}
