package com.palette.palettepetsback.hotSpot.repository;

import com.palette.palettepetsback.hotSpot.entity.HotSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSpotRepository extends JpaRepository<HotSpot, Long> {
    // 여기에 추가적인 메소드를 정의할 수 있습니다.
    // 예를 들어, 이름으로 HotSpot을 찾는 메소드를 추가할 수 있습니다.
    // List<HotSpot> findByName(String name);
}