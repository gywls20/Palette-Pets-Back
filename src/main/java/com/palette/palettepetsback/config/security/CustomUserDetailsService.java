package com.palette.palettepetsback.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
//    private final MemberRepository memberRepository;
    // todo 회원 레포지토리 등록 및 메서드 수정 필요

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        log.info("- 추후 회원 레포지토리에서 가져온 Member 엔티티를 CustomUserDetails 필드로 넣어서 정보 등록");
        return new CustomUserDetails();
    }
}
