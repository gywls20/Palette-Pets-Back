package com.palette.palettepetsback.hotSpot.dto.request;

// 필요한 라이브러리 임포트
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class HotSpotAddRequest {

    @NotBlank(message = "명소의 이름은 필수 입력 항목입니다.")
    private String name;

    @Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
    private String description;

    @NotBlank(message = "장소는 필수 입력 항목입니다.")
    private String place;

    @Size(max = 500, message = "추천 이유는 500자를 초과할 수 없습니다.")
    private String reason;

    @NotBlank(message = "애완동물 유형은 필수 입력 항목입니다.")
    private String petType;
}

