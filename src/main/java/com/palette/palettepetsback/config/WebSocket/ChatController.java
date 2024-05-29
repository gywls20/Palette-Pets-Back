package com.palette.palettepetsback.config.WebSocket;

import com.palette.palettepetsback.Chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/app")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRepository chatRepository;

    @MessageMapping("/chat") // /app/chat 경로로 메세지를 보내면 해당 메서드가 처리
    public void sendMessage(@Payload ChatMessage message) { //ChatMessage 객체를 받아서 처리
        String destination = determineDestination(message);
        simpMessagingTemplate.convertAndSend(destination, message); //해당하는 경로로 메세지를 전송한다.
        chatRepository.save("chat", message);
    }
    private String determineDestination(ChatMessage message){
        log.info("message : {}", message.getContent());
        return "/topic/messages"; //해당 경로를 지정한다.
    }
}
