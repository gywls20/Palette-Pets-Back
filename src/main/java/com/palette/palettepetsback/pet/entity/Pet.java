package com.palette.palettepetsback.pet.entity;

import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.pet.dto.request.PetUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    // 나중에 member_id 연관관계 맺기
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_who", referencedColumnName = "member_id")
//    private Member member;
    private Long createdWho;
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
    private List<ImgPet> petImageList = new ArrayList<>();

    @Builder
    public Pet(Long createdWho, String petName, String petImage, String petCategory1, String petCategory2, String petBirth, String petGender, Integer petWeight) {
        this.createdWho = createdWho;
        this.petName = petName;
        this.petImage = petImage;
        this.petCategory1 = petCategory1;
        this.petCategory2 = petCategory2;
        this.petBirth = petBirth;
        this.petGender = petGender;
        this.petWeight = petWeight;
    }

    // 펫 수정 메서드 (전체)
    public void updatePet(PetUpdateDto dto) {
        this.createdWho = dto.getCreatedWho();
        this.petName = dto.getPetName();
        this.petImage = dto.getPetImage();
        this.petCategory1 = dto.getPetCategory1();
        this.petCategory2 = dto.getPetCategory2();
        this.petBirth = dto.getPetBirth();
        this.petGender = dto.getPetGender();
        this.petWeight = dto.getPetWeight();
    }

    /**
     * 펫 생일 -> LocalDateTime / mysql은 date
     * 펫 젠더 -> 0 / 1  : true and false 가 낫지않을까? -> Boolean
     * 펫 카테고리 1-2는 뭐임 -> enum 나중에 고쳐보는것도 좋을듯
     *
     */
}