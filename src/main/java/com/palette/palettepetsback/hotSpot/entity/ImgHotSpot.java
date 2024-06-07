package com.palette.palettepetsback.hotSpot.entity;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "img_hot_spot") // 테이블명
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImgHotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_hot_spot_id")
    private Long id;
    @Column(name = "img_url") // Object Storage 에 저장한 file url
    private String imgUrl;
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "hot_spot_id") // 이미지 연결 명소 게시물
    private HotSpot hotSpot;
    private Boolean isDeleted = false;
}
