package com.palette.palettepetsback.hotSpot.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "hot_spot") // 테이블명 추가
public class HotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_spot_id") // 컬럼명 추가
    private Long id;

    @Column(name = "place_name") // 컬럼명 추가
    private String name;

    @Column(name = "content") // 컬럼명 추가
    private String description;

    @Column(name = "place") // 컬럼명 추가
    private String place;

    @Column(name = "simple_content") // 컬럼명 추가
    private String reason;

    @Column(name = "petType") // 컬럼명 추가
    private String petType;

    @Column(name = "member_id") // 컬럼명 추가
    private Long memberId;

    @Column(name = "created_at") // 컬럼명 추가
    private Date createdAt;

    @Column(name = "modified_at") // 컬럼명 추가
    private Date modifiedAt;

    @Column(name = "address") // 컬럼명 추가
    private String address;

    @Column(name = "lat") // 컬럼명 추가
    private Double lat;

    @Column(name = "lng") // 컬럼명 추가
    private Double lng;

    @Column(name = "count_views") // 컬럼명 추가
    private Integer countViews;

    @Column(name = "is_deleted") // 컬럼명 추가
    private Boolean isDeleted;

    // 다른 필드들도 필요에 따라 추가해야 합니다.
}
