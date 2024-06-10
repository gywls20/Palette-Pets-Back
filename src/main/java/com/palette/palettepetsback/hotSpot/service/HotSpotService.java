package com.palette.palettepetsback.hotSpot.service;


import com.palette.palettepetsback.config.exceptions.NoMemberExistException;
import com.palette.palettepetsback.hotSpot.dto.request.HotSpotAddRequest;
import com.palette.palettepetsback.hotSpot.dto.request.HotSpotUpdateRequest;
import com.palette.palettepetsback.hotSpot.dto.response.HotSpotListResponse;
import com.palette.palettepetsback.hotSpot.dto.response.HotSpotResponse;
import com.palette.palettepetsback.hotSpot.dto.response.ImgHotSpotResponse;
import com.palette.palettepetsback.hotSpot.entity.HotSpot;
import com.palette.palettepetsback.hotSpot.entity.ImgHotSpot;
import com.palette.palettepetsback.hotSpot.repository.HotSpotRepository;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotSpotService {
    private final HotSpotRepository hotSpotRepository;
    private final MemberRepository memberRepository;

    //hotspot 저장 메서드
    @Transactional
    public void HotSpotInsert(HotSpotAddRequest dto) {

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new NoMemberExistException("존재하지 않는 회원입니다."));

        HotSpot hotSpot = HotSpot.builder()
                .lng(dto.getLng())
                .lat(dto.getLat())
                .simpleContent(dto.getSimpleContent())
                .address(dto.getAddress())
                .content(dto.getContent())
                .placeName(dto.getPlaceName())
                .member(member)
                .build();

        hotSpotRepository.save(hotSpot);
    }

    //hotspot 업데이트 메서드
    @Transactional
    public void HotSpotUpdate(HotSpotUpdateRequest dto) {

        HotSpot hotSpot = hotSpotRepository.findById(dto.getHotSpotId())
                .orElseThrow(() -> new RuntimeException("명소 추천 게시글이 존재하지 않습니다."));

        hotSpot.updateHotSpot(dto);
    }

    //hotspot 삭제 메서드
    @Transactional
    public void HotSpotDelete(Long id){
        HotSpot hotSpot = hotSpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("명소 추천 게시글이 존재하지 않습니다."));

        hotSpot.changeIsDeleted();
    }

    //hotspot 리스트 쿼리 메서드
    public List<HotSpotListResponse> getAllHotSpot(){
        List<HotSpot> hotSpotList = hotSpotRepository.findHotSpotList();

        //controller로 반환할 빈 dto 리스트 생성
        List<HotSpotListResponse> list = new ArrayList<>();

        for (HotSpot hotSpot : hotSpotList) {
            HotSpotListResponse dto = hotSpot.toDto();
            list.add(dto);
        }

        return list;
    }
    //hotspot 한 건 쿼리 메서드 -> imageHotSpot 값도 같이 필요
    public HotSpotResponse getHotSpotDetail(Long hotSpotId){

        HotSpot hotSpot = hotSpotRepository.findById(hotSpotId)
                .orElseThrow(() -> new RuntimeException("명소 추천 게시글이 존재하지 않습니다."));

        List<ImgHotSpot> imgHotSpots = hotSpot.getImgHotSpots();

        List<ImgHotSpotResponse> imgHotSpotDtoList = new ArrayList<>();

        for (ImgHotSpot imgHotSpot : imgHotSpots) {
            ImgHotSpotResponse dto = ImgHotSpotResponse.builder()
                    .imgHotSpotId(imgHotSpot.getId())
                    .imgUrl(imgHotSpot.getImgUrl())
                    .build();

            imgHotSpotDtoList.add(dto);
        }

        HotSpotResponse result = HotSpotResponse.builder()
                .hotSpotId(hotSpot.getId())
                .memberNickname(hotSpot.getMember().getMemberNickname())
                .createAt(hotSpot.getCreatedAt())
                .modifiedAt(hotSpot.getModifiedAt())
                .placeName(hotSpot.getPlaceName())
                .simpleContent(hotSpot.getSimpleContent())
                .content(hotSpot.getContent())
                .address(hotSpot.getAddress())
                .lat(hotSpot.getLat())
                .lng(hotSpot.getLng())
                .countViews(hotSpot.getCountViews())
                .imgList(imgHotSpotDtoList)
                .build();

        return result;
    }
    //조회수 기능 추가

}