package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 아이디로 찾기
    Optional<Member> findByEmail(String email);
    // 아이디 중복 여부
    Boolean existsByMemberId(Long memberId);

    Optional<Object> findByMemberName(String name);
}
