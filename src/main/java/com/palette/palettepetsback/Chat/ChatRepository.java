package com.palette.palettepetsback.Chat;

import com.palette.palettepetsback.config.Redis.Test;
import com.palette.palettepetsback.config.WebSocket.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String id, ChatMessage message){
        // 해당 해시 키 안의 id, test 값으로 값을 저장한다.
        // 만약 key가 이미 존재한다면 update를 수행한다.
        redisTemplate.opsForHash().put("Chatting", id, message);
    }
}
