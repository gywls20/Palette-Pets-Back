package com.palette.palettepetsback.config.WebSocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {
    private String sender;
    private String content;
}
