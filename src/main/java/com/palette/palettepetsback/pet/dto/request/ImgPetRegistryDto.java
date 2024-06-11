package com.palette.palettepetsback.pet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImgPetRegistryDto {

    @NotBlank
    private String imgUrl; // S3에 저장한 주소값
    @NotNull
    private Long petId; // 펫 아이디
}
