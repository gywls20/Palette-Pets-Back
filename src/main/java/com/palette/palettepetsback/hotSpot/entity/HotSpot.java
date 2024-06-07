package com.palette.palettepetsback.hotSpot.entity;

import com.palette.palettepetsback.member.entity.Member;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "hot_spot") // 테이블명
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_spot_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 작성자 -> 회원 (ADMIN만 쓰게 하기)
    private Member member;
    @Column(name = "created_at") // 생성일
    private LocalDateTime createdAt;
    @Column(name = "modified_at") // 수정일
    private LocalDateTime modifiedAt;
    @Column(name = "place_name") // 장소 명
    private String placeName;
    @Column(name = "simple_content") // 내용 미리보기
    private String simpleContent;
    @Column(name = "content") // 내용
    private String content;
    @Column(name = "address", length = 500) // 주소
    private String address;
    @Column(name = "lat") //위도
    private Double lat;
    @Column(name = "lng") //경도
    private Double lng;
    @Column(name = "count_views") // 조회수
    private Integer countViews;
    @Column(name = "is_delteed") // 삭제
    private Boolean isDeleted;

    // 명소 추천 글 등록 메서드
    @Builder
    public HotSpot(Member member, String placeName, String simpleContent, String content, String address, Double lat, Double lng) {
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.placeName = placeName;
        this.simpleContent = simpleContent;
        this.content = content;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.countViews = 0;
        this.isDeleted = false;
    }
}
