package com.palette.palettepetsback.Chat.Service;

import com.palette.palettepetsback.Chat.Repository.ChatRepository;
import com.palette.palettepetsback.config.jwt.jwtAnnotation.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public String getChatRoom(Long my_id, Long receiver_id){
        String room_name = chatRepository.findChatRoomById(my_id, receiver_id).orElseThrow(()->new IllegalArgumentException("속하지 않은 채팅방입니다."));
        return room_name;
    }

//    @Transactional
//    public String createChatRoom(Long user1, Long user2){
//        return chatRepository.save(user1, user2);
//    }
}
