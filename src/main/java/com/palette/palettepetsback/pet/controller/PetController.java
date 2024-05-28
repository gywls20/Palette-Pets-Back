package com.palette.palettepetsback.pet.controller;

import com.palette.palettepetsback.pet.dto.request.ImgPetRegistryDto;
import com.palette.palettepetsback.pet.dto.request.PetRegistryDto;
import com.palette.palettepetsback.pet.dto.request.PetUpdateDto;
import com.palette.palettepetsback.pet.dto.response.PetResponseDto;
import com.palette.palettepetsback.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    // 한 회원이 등록한 반려동물 정보 리스트 쿼리
    @GetMapping("")
    public List<PetResponseDto> getPetListByMemberId(@RequestParam("memberId") Long memberId) {
        return petService.findAllByMemberId(memberId);
    }

    // 펫 등록
    @PostMapping("")
    public boolean registerPet(@RequestBody PetRegistryDto dto) {
        // todo S3 저장 메서드 + 펫 이미지 리스트 저장
        return petService.registerPet(dto) != null;
    }

    // 펫 이미지 등록
    @PostMapping("/img")
    public boolean registerPetImg(@RequestBody ImgPetRegistryDto dto) {
        // todo S3 저장 메서드 + 펫 이미지 리스트 저장
        return petService.registerImgPet(dto) != null;
    }

    // 펫 수정 - 펫 + 펫 이미지 추가
    @PutMapping("/{petId}")
    public boolean updatePet(@PathVariable("petId") Long petId, PetUpdateDto dto) {
        petService.updatePet(dto);
        // todo S3 저장 메서드 + 추가 펫 이미지 리스트 저장
        return true;
    }

    // 펫 삭제
    @DeleteMapping("{petId}")
    public boolean deletePet(@PathVariable("petId") Long petId) {
        petService.deletePet(petId);
        return true;
    }

    // 펫 이미지 삭제
    @DeleteMapping("{petId}/img")
    public boolean deletePetImg(@PathVariable("petId") Long petId, List<Long> imgIds) {
        petService.deleteImgPet(imgIds);
        return true;
    }
}
