package com.palette.palettepetsback.pet.repository;

import com.palette.palettepetsback.pet.dto.response.ImgPetResponseDto;
import com.palette.palettepetsback.pet.entity.ImgPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImgPetRepository extends JpaRepository<ImgPet, Long> {

    // 마이페이지용 회원의 반려 동물의 모든 이미지 리스트 쿼리
    @Query("select new com.palette.palettepetsback.pet.dto.response.ImgPetResponseDto(ip.id, ip.imgUrl, p.id) " +
            "from ImgPet ip " +
            "join ip.pet p " +
            "join p.member m " +
            "where m.memberNickname = :memberNickname")
    List<ImgPetResponseDto> findAllByMemberId(@Param("memberNickname") String memberNickname);

    List<ImgPet> findAllByPetId(Long petId);
    Optional<ImgPet> findByImgUrl(String imgUrl);
}
