package com.palette.palettepetsback.carrot.repository;

import com.palette.palettepetsback.carrot.domain.Carrot;
import com.palette.palettepetsback.carrot.domain.CarrotLike;
import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarrotLikeRepository extends JpaRepository<CarrotLike, Long> {
    Optional<CarrotLike> findByCarrotIdAndMember (Carrot carrot, Member member);

    @Query("select count(*) from CarrotLike c where c.carrotId.carrotId = :id and c.member.memberId = :memberId")
    int likeState(Long id, Long memberId);

    List<CarrotLike> findByMember (Member member);
}
