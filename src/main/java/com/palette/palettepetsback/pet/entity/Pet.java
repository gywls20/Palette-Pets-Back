package com.palette.palettepetsback.pet.entity;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Column(name = "created_who")
    private Member member;
    private String petName;
    private String petImage;
    @Column(name = "pet_category_1")
    private String petCategory1;
    @Column(name = "pet_category_2")
    private String petCategory2;
    private String petBirth;
    private String petGender;
    private Integer petWeight;
    // 양방향 연관관계
    @OneToMany(mappedBy = "pet", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ImgPet> petImages = new ArrayList<>();

    /**
     * 펫 생일 -> LocalDateTime / mysql은 date
     * 펫 젠더 -> 0 / 1  : true and false 가 낫지않을까? -> Boolean
     * 펫 카테고리 1-2는 뭐임 -> enum 나중에 고쳐보는것도 좋을듯
     *
     */
}