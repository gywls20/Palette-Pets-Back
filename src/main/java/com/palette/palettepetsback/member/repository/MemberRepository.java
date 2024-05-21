package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    // 아이디로 찾기
    Optional<Member> findByMemberId(String memberId);
    // 아이디 중복 여부
    Boolean existsByMemberId(String memberId);
}
