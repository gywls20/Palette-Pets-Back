package com.palette.palettepetsback.carrot.repository;

import com.palette.palettepetsback.carrot.domain.Carrot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarrotRepository extends JpaRepository<Carrot, Long> {
    @Modifying
    @Query("update Carrot c set c.carrotView = c.carrotView + 1 where c.carrotId = :carrotId")
    int updateView(Long carrotId);
}
