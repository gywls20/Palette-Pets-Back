package com.palette.palettepetsback.config.WebSocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/app")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        String destination = determineDestination(message);
        simpMessagingTemplate.convertAndSend(destination, message);
    }
    private String determineDestination(ChatMessage message){
        log.info("message : {}", message.getContent());
        return "/topic/" + message.getContent();
    }
}
