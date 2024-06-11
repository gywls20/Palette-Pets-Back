package com.palette.palettepetsback.pet.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "img_pet")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImgPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;
    private String imgUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Builder
    public ImgPet(String imgUrl, Pet pet) {
        this.imgUrl = imgUrl;
        this.pet = pet;
    }
}
