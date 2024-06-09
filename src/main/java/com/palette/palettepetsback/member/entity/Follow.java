package com.palette.palettepetsback.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "follow")
public class Follow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_follow_id")
    private Long memberFollowId;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Member followerId;

    @ManyToOne
    @JoinColumn(name = "followee_id")
    private Member followeeId;

    @CreationTimestamp
    @Column(name = "updated_at")
    private LocalDateTime time;

    public void saveFollow(Member followerId, Member followeeId){
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}