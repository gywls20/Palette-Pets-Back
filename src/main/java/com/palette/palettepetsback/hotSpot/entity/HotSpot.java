package com.palette.palettepetsback.hotSpot.entity;

import com.palette.palettepetsback.member.entity.Member;
import lombok.*;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "hot_spot") // 테이블명
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"member"})
public class HotSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hot_spot_id") // 장소 아이디
    private Long id;
    // 이제 멤버랑 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 글쓴이
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    @Column(name = "place_name") // 장소이름
    private String placeName;
    private String simpleContent;
    private String content;
    @Column(length = 500)
    private String address;
    private Double lat;
    private Double lng;
    private Integer countViews; // 조회수
    private Boolean isDeleted;
}
