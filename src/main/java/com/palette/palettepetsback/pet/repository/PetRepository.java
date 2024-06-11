package com.palette.palettepetsback.pet.repository;

import com.palette.palettepetsback.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByPetName(String petName);

    @Query("select p from Pet p where p.member.memberId = :createdWho")
    List<Pet> findByCreatedWho(@Param("createdWho") Long createdWho); // 멤버에 관련된 pet 정보 조회
}
