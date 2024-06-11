package com.palette.palettepetsback.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberIssueResponse {

    private Long memberIssueId;
    private Long receiverId;
    private String receiverNickName;
    private LocalDateTime createdAt;
    private String issueContent;
    /**
     * 0 : 연결, 1 : 채팅요청, 2 : 글 알람, 3 : 댓글 알람, 4.거래완료...
     */
    private Integer issueCode;
    private boolean isRead;

}
