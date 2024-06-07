package com.palette.palettepetsback.config.Mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class EmailResponseDTO{
    private final String data;

    @Getter @Setter
    @NoArgsConstructor
    public static class sendPwDto {
        private String address;
        private String title;
        private String message;
    }
}
