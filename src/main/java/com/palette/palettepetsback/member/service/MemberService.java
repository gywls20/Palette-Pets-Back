package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
import com.palette.palettepetsback.member.dto.JoinRequest;
import com.palette.palettepetsback.member.dto.LoginRequest;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import com.palette.palettepetsback.member.repository.MemberRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //일반 로그인
    public Member login(String email, String password) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            System.out.println("그런 사람 없습니다.");
            return null;
        }else {
            Member member1 = member.get();

            // passwordEncoder.matches를 사용하여 비밀번호 비교
            if(passwordEncoder.matches(password, member1.getPassword())){
                return member1;
            }
        }
        System.out.println("비번 잘못 쳤습니다.");
        return null;
    }
    //이메일 중복확인
    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }
    //닉네임 중복확인
    public boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByMemberNickname(nickname);
    }
    //회원가입
    public void join(JoinRequest req) {
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        memberRepository.save(req.toEntity(encodedPassword));

    }

}
