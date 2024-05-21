package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    
    public Member test(String memberId) {
        
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NoMemberExistException("몽총이"));
    }

}
