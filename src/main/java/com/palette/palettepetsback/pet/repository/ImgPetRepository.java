package com.palette.palettepetsback.pet.repository;

import com.palette.palettepetsback.pet.entity.ImgPet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImgPetRepository extends JpaRepository<ImgPet, Long> {

    List<ImgPet> findAllByPetId(Long petId);
    Optional<ImgPet> findByImgUrl(String imgUrl);
}
