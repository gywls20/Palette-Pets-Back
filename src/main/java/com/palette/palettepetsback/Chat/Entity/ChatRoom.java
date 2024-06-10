package com.palette.palettepetsback.Chat.Entity;

import com.palette.palettepetsback.Article.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "chat_room")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @Column(name = "chat_room_id", columnDefinition = "BINARY", length = 16)
    private byte[] chatRoomId;

    @Column(name = "user1")
    private Long user1;

    @Column(name = "user2")
    private Long user2;

    @PrePersist //Entity 실행 전 수행하는 마라미터로 default 값을 지정O
    public void prePersist(){
        this.chatRoomId = UUID.randomUUID().toString().getBytes();
    }
}
