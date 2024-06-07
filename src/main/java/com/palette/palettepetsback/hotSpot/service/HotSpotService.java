package com.palette.palettepetsback.hotSpot.service;

import com.palette.palettepetsback.hotSpot.dto.request.HotSpotAddRequest;
import com.palette.palettepetsback.hotSpot.dto.response.HotSpotResponse;
import com.palette.palettepetsback.hotSpot.entity.HotSpot;
import com.palette.palettepetsback.hotSpot.repository.HotSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotSpotService {

    @Autowired
    private HotSpotRepository hotSpotRepository;

    // 게시글 추가
//    public HotSpotResponse addHotSpot(HotSpotAddRequest hotSpotAddRequest) {
//        HotSpot hotSpot = new HotSpot();
//        hotSpot.setName(hotSpotAddRequest.getName());
//        hotSpot.setDescription(hotSpotAddRequest.getDescription());
//        hotSpot.setPlace(hotSpotAddRequest.getPlace());
//        hotSpot.setReason(hotSpotAddRequest.getReason());
//        hotSpot.setPetType(hotSpotAddRequest.getPetType());
//
//        // 작성자를 현재 사용자로 설정
//        String username = getCurrentUsername();
//        hotSpot.setCreatedBy(username);
//
//        hotSpotRepository.save(hotSpot);
//        return new HotSpotResponse();
//    }

    // 태그 조회
//    public HotSpotResponse getHotSpot(Long id) {
//        Optional<HotSpot> hotSpot = hotSpotRepository.findById(id);
//        if (hotSpot.isPresent()) {
//            return new HotSpotResponse();
//        }
//        return null;
//    }
//
//    // 목록 조회
//    public List<HotSpotResponse> getAllHotSpots() {
//        List<HotSpot> hotSpots = hotSpotRepository.findAll();
//        return new ArrayList<>();
//    }
//
//    // 게시글 수정
//    public HotSpotResponse updateHotSpot(Long id, HotSpotAddRequest hotSpotUpdateRequest) {
//        Optional<HotSpot> optionalHotSpot = hotSpotRepository.findById(id);
//        if (optionalHotSpot.isPresent()) {
//            HotSpot hotSpot = optionalHotSpot.get();
//            String username = getCurrentUsername();
//
//            // 작성자 또는 관리자인지 확인
//            if (!hotSpot.getCreatedBy().equals(username) && !isAdmin(username)) {
//                throw new SecurityException("수정 권한이 없습니다.");
//            }
//
//            hotSpot.setName(hotSpotUpdateRequest.getName());
//            hotSpot.setDescription(hotSpotUpdateRequest.getDescription());
//            hotSpot.setPlace(hotSpotUpdateRequest.getPlace());
//            hotSpot.setReason(hotSpotUpdateRequest.getReason());
//            hotSpot.setPetType(hotSpotUpdateRequest.getPetType());
//
//            hotSpotRepository.save(hotSpot);
//            return new HotSpotResponse();
//        }
//        return null;
//    }
//
//    // 게시글 삭제
//    public void deleteHotSpot(Long id) {
//        hotSpotRepository.deleteById(id);
//    }
//
//    // 현재 사용자명 가져오기
//    private String getCurrentUsername() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();
//        }
//        return principal.toString();
//    }
//
//    // 관리자 여부 확인
//    private boolean isAdmin(String username) {
//        // 관리자인지 확인하는 로직 구현
//        // 예시: 관리자 사용자명을 "admin"으로 가정
//        return "admin".equals(username);
//    }
}