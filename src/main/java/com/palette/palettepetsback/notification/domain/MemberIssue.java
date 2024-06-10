package com.palette.palettepetsback.notification.domain;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member_issue")
@ToString(exclude = {"receiver"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_who", referencedColumnName = "member_id")
    private Member receiver;
    private LocalDateTime createdAt;
    private String issueContent;
    /**
     * 0 : 연결, 1 : 채팅요청, 2 : 글 알람, 3 : 댓글 알람, 4.거래완료...
     */
    private Integer issueCode;
    private boolean isRead;

    // 기본 회원 알람 기능 생성자 메서드
    @Builder
    public MemberIssue(Member receiver, String issueContent, Integer issueCode) {
        this.receiver = receiver;
        this.createdAt = LocalDateTime.now();
        this.issueContent = issueContent;
        this.issueCode = issueCode;
        this.isRead = false;
    }

    public void changeIsRead() {
        this.isRead = true;
    }
}
