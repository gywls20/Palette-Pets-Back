package com.palette.palettepetsback.hotSpot.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImgHotSpotResponse {

    private Long imgHotSpotId;
    private String imgUrl;
}
