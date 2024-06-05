package com.palette.palettepetsback.hotSpot.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HotSpotResponse {
    private Long id; // 장소 아이디
    private String name; // 장소이름
    private String description; // 내용
    private String place; // 장소
    private String reason; // 내용 미리보기
    private String petType; // 종류
    private Long memberId; // 회원 아이디
    private Date createdAt; // 생성일
    private Date modifiedAt; // 수정일
    private String address; // 주소
    private Double lat; // 위도
    private Double lng; // 경도
    private Integer countViews; // 조회수
    private Boolean isDeleted; // 삭제 여부
    private Object createdBy; // 작성자

}
