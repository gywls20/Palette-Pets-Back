package com.palette.palettepetsback.hotSpot.dto.response;

import com.palette.palettepetsback.hotSpot.entity.HotSpot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImgHotSpotResponse {

    private Long imgHotSpotId;
    private String imgUrl;
}
