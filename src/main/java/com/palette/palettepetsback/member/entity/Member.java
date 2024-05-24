package com.palette.palettepetsback.member.entity;

import com.palette.palettepetsback.config.auditing.BaseEntity;
import com.palette.palettepetsback.member.dto.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;
    private String memberId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    @Builder
    public Member(String memberId, String name, Role role, String password) {
        this.memberId = memberId;
        this.name = name;
        this.role = role;
        this.password = password;
    }
}
