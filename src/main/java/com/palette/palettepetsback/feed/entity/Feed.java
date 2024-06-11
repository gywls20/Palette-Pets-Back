package com.palette.palettepetsback.feed.entity;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long feedId;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<FeedImg> feedImageList = new ArrayList<>();
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    @CreationTimestamp
    private LocalDateTime time;

    public void saveFeed(String text, Member memberId) {
        this.text = text;
        this.memberId=memberId;
    }
}
