package com.palette.palettepetsback.hotSpot.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class HotSpotUpdateRequest {

    @NotNull
    private Long hotSpotId;
    @NotBlank(message = " 장소명은 반드시 기재하셔야 합니다.")
    private String placeName;
    private String simpleContent;
    private String content;
    private String address;
    private Double lat;
    private Double lng;
}