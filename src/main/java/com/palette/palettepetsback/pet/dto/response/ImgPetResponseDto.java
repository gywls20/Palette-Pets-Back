package com.palette.palettepetsback.pet.dto.response;

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
}
