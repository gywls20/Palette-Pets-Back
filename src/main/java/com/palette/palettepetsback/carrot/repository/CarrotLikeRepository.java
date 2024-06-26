package com.palette.palettepetsback.carrot.repository;

import com.palette.palettepetsback.carrot.domain.Carrot;
import com.palette.palettepetsback.carrot.domain.CarrotLike;
import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrotLikeRepository extends JpaRepository<CarrotLike, Long> {
    Optional<CarrotLike> findByCarrotIdAndMember (Carrot carrot, Member member);
    List<CarrotLike> findByMember (Member member);

}
