package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
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

    private final MemberRepositoryCustomImpl memberRepositoryCustom;

//    public Member login(String email, String password) {
//        Optional<Member> findMember = memberRepositoryCustom.findByEmail(email);
//        if(!findMember.orElseThrow(()->new NotCorrespondingEmailException("해당 이메일이 존재하지 않습니다.")).checkPassword(password)){
//            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
//        }
//        return findMember.get();
//    }

}
