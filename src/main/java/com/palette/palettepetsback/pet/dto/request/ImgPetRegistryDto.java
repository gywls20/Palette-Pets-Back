package com.palette.palettepetsback.pet.dto.request;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImgPetRegistryDto {

    private String imgUrl; // S3에 저장한 주소값
    private Long petId; // 펫 아이디
}
