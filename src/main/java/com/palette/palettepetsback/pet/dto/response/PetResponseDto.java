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
    private Long createdWho;
    private String petName;
    private String petImage;
    private String petCategory1;
    private String petCategory2;
    private String petBirth;
    private String petGender;
    private Integer petWeight;
    private List<ImgPetResponseDto> petImgList;

}
