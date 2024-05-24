package com.palette.palettepetsback.pet.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PetUpdateDto {
    /**
     * Pet 수정 요청 Dto
     */
    private Long petId;
    private Long createdWho; // 나중에 member_id 연관관계 맺기
    private String petName;
    private String petImage;
    private String petCategory1;
    private String petCategory2;
    private String petBirth;
    private String petGender;
    private Integer petWeight;

}
