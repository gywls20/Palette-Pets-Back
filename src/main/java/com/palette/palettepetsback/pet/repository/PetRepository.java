package com.palette.palettepetsback.pet.repository;

import com.palette.palettepetsback.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByPetName(String petName);
    List<Pet> findByCreatedWho(Long createdWho); // 멤버에 관련된 pet 정보 조회
}
