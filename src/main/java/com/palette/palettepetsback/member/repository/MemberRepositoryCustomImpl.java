package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.palette.palettepetsback.member.entity.QMember.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // test인 이름을 가진 회원 쿼리
    @Override
    public Member queryDslTest() {
        return queryFactory.selectFrom(member)
                .where(member.memberId.eq("test"))
                .fetchOne();
    }
}
