package com.palette.palettepetsback.hotSpot.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "img_hot_spot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImgHotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_hot_spot_id")
    private Long id;
    private String imgUrl;
    private LocalDateTime createdAt;
    // 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_spot_id")
    private HotSpot hotSpot;
    private boolean isDeleted = false;

}
