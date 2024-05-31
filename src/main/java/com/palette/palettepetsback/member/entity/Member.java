package com.palette.palettepetsback.member.entity;

import com.palette.palettepetsback.member.dto.Role;
import com.palette.palettepetsback.pet.entity.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Pet> pets = new ArrayList<>();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String email;
    private String password;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "member_nickname")
    private String memberNickname;
    @Column(name = "member_address")
    private String memberAddress;
    @Column(name = "member_birth")
    private String memberBirth;
    @Column(name = "member_gender")
    private String memberGender;
    @Column(name = "member_phone")
    private String memberPhone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "member_image")
    private String memberImage;
    @Column(name = "login_type")
    private String loginType;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public Member(String email, String password, String memberName,String memberNickname, String memberAddress,
                  String memberGender, String memberPhone,
                  String memberImage,
                  Role role ){
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberAddress = memberAddress;
        this.memberGender = memberGender;
        this.memberPhone = memberPhone;
        this.memberImage = memberImage;
        this.role = role;
    }


    //근데 이건 어따 쓰는 거임?? 규찬님?
    public Member(Long memberId, String email, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.role = role;
    }


}
