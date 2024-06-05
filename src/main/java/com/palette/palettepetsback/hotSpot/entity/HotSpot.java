package com.palette.palettepetsback.hotSpot.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "hot_spot") // 테이블명

public class HotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_spot_id") // 장소 아이디
    private Long id;

    @Column(name = "place_name") // 장소이름
    private String name;

    @Column(name = "content") // 내용
    private String description;

    @Column(name = "place") // 장소

    private String place;

    @Column(name = "simple_content") // 내용 미리보기

    private String reason;

    @Column(name = "petType") // 종류

    private String petType;

    @Column(name = "member_id") // 회원 아이디

    private Long memberId;

    @Column(name = "created_at") // 컬럼명

    private Date createdAt;

    @Column(name = "modified_at") // 컬럼명

    private Date modifiedAt;

    @Column(name = "address") // 주소

    private String address;

    @Column(name = "lat") //위도

    private Double lat;

    @Column(name = "lng") //경도

    private Double lng;

    @Column(name = "count_views") // 조회수

    private Integer countViews;

    @Column(name = "is_deleted") // 삭제

    private Boolean isDeleted;

    @Column(name = "setCreatedBy") //작성자

    private Object createdBy;

    // 다른 필드들도 필요에 따라
    // 해야 합니다.
}
