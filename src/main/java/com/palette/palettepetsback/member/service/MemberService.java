package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
import com.palette.palettepetsback.member.dto.LoginRequest;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import com.palette.palettepetsback.member.repository.MemberRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //일반 로그인
    public Member login(String email, String password) {

        Optional<Member> member = memberRepository.findByEmail(email);


        if (member.isEmpty()) {
            System.out.println("그런 사람 없습니다.");
            return null;
        }else {
            Member member1 = member.get();

            if(member1.getPassword().equals(password)){
                return member1;
            }
        }
        System.out.println("비번 잘못 쳤습니다.");
        return null;
    }

//    public Member login(String email, String password) {
//        Optional<Member> findMember = memberRepositoryCustom.findByEmail(email);
//        if(!findMember.orElseThrow(()->new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(password)){
//            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
//        }
//        return findMember.get();
//    }

}
