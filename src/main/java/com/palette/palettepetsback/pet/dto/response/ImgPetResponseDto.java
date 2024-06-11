package com.palette.palettepetsback.pet.dto.response;

import com.palette.palettepetsback.pet.entity.ImgPet;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImgPetResponseDto {

    private Long imgPetId;
    private String imgUrl;
    private Long petId;

    // entity to dto
    public static ImgPetResponseDto toDto(ImgPet imgPet) {
        return ImgPetResponseDto.builder()
                .imgPetId(imgPet.getId())
                .imgUrl(imgPet.getImgUrl())
                .petId(imgPet.getPet().getId())
                .build();
    }
}
