package com.palette.palettepetsback.hotSpot.entity;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "hot_spot_star_point")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HotSpotStarPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_spot_star_point_id")
    private Long id;
    private Integer rating;
    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hot_spot_id")
    private HotSpot hotSpot;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

}
