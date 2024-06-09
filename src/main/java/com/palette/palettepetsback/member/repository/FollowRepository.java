package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Follow;
import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFollowerIdAndFolloweeId(Member followerId, Member followeeId);

}
