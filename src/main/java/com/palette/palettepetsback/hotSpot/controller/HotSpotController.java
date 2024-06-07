//package com.palette.palettepetsback.hotSpot.controller;
//
//import com.palette.palettepetsback.hotSpot.dto.request.HotSpotAddRequest;
//import com.palette.palettepetsback.hotSpot.dto.response.HotSpotListResponse;
//import com.palette.palettepetsback.hotSpot.service.HotSpotService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/hotspots")
//public class HotSpotController {
//
//    @Autowired
//    private HotSpotService hotSpotService;
//
//    @PostMapping // 작성
//    public ResponseEntity<HotSpotListResponse> addHotSpot(@Validated @RequestBody HotSpotAddRequest hotSpotAddRequest) {
//        HotSpotListResponse hotSpotListResponse = hotSpotService.addHotSpot(hotSpotAddRequest);
//        return ResponseEntity.ok(hotSpotListResponse);
//    }
//
//    @GetMapping("/{tag}") // 태그 조회
//    public ResponseEntity<HotSpotListResponse> getHotSpot(@PathVariable Long tag) {
//        HotSpotListResponse hotSpotListResponse = hotSpotService.getHotSpot(tag);
//        return ResponseEntity.ok(hotSpotListResponse);
//    }
//
//    @GetMapping // 리스트 호출
//    public ResponseEntity<List<HotSpotListResponse>> getAllHotSpots() {
//        List<HotSpotListResponse> hotSpots = hotSpotService.getAllHotSpots();
//        return ResponseEntity.ok(hotSpots);
//    }
//
//    @PutMapping("/{id}") // 수정
//    public ResponseEntity<HotSpotListResponse> updateHotSpot(@PathVariable Long id, @Validated @RequestBody HotSpotAddRequest hotSpotAddRequest) {
//        HotSpotListResponse hotSpotListResponse = hotSpotService.updateHotSpot(id, hotSpotAddRequest);
//        return ResponseEntity.ok(hotSpotListResponse);
//    }
//
//    @DeleteMapping("/{id}") // 삭제
//    public ResponseEntity<Void> deleteHotSpot(@PathVariable Long id) {
//        hotSpotService.deleteHotSpot(id);
//        return ResponseEntity.noContent().build();
//    }
//}