package com.palette.palettepetsback.hotSpot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotSpotRecentDTO {
    private Long hotSpotId;
    private Long userId;
    private String placeName;
    private Object uploadAt;

    private String imgUrl;
    public HotSpotRecentDTO(Long hotSpotId, Long userId, String placeName, Object uploadAt) {
        this.hotSpotId = hotSpotId;
        this.userId = userId;
        this.placeName = placeName;
        this.uploadAt = uploadAt;
    }
}
