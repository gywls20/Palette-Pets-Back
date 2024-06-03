package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.palette.palettepetsback.member.entity.QMember.member;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // test인 이름을 가진 회원 쿼리
    @Override
    public Member queryDslTest() {
        return queryFactory.selectFrom(member)
                .where(member.email.eq("test"))
                .fetchOne();
    }

    public Optional<Member> findByEmail(String email) {
        QMember member = QMember.member;

        Member result = queryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}