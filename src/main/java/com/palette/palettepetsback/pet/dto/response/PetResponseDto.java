package com.palette.palettepetsback.pet.dto.response;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponseDto {

    private Long petId;
    private Long createdWho; // member 의 email 혹은 member_id
    private String petName;
    private String petImage;
    private String petCategory1; // 대분류 : ex) 개, 고양이, 햄스터
    private String petCategory2; // 중분류 : ex) 치와와, 리트리버 ...
    private String petBirth;
    private String petGender;
    private Integer petWeight;
    private List<ImgPetResponseDto> petImgList;

}
