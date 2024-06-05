package com.palette.palettepetsback.hotSpot.controller;

import com.palette.palettepetsback.hotSpot.dto.request.HotSpotAddRequest;
import com.palette.palettepetsback.hotSpot.dto.response.HotSpotResponse;
import com.palette.palettepetsback.hotSpot.service.HotSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotspots")
public class HotSpotController {

    @Autowired
    private HotSpotService hotSpotService;

    @PostMapping // 작성
    public ResponseEntity<HotSpotResponse> addHotSpot(@Validated @RequestBody HotSpotAddRequest hotSpotAddRequest) {
        HotSpotResponse hotSpotResponse = hotSpotService.addHotSpot(hotSpotAddRequest);
        return ResponseEntity.ok(hotSpotResponse);
    }

    @GetMapping("/{tag}") // 태그 조회
    public ResponseEntity<HotSpotResponse> getHotSpot(@PathVariable Long tag) {
        HotSpotResponse hotSpotResponse = hotSpotService.getHotSpot(tag);
        return ResponseEntity.ok(hotSpotResponse);
    }

    @GetMapping // 리스트 호출
    public ResponseEntity<List<HotSpotResponse>> getAllHotSpots() {
        List<HotSpotResponse> hotSpots = hotSpotService.getAllHotSpots();
        return ResponseEntity.ok(hotSpots);
    }

    @PutMapping("/{id}") // 수정
    public ResponseEntity<HotSpotResponse> updateHotSpot(@PathVariable Long id, @Validated @RequestBody HotSpotAddRequest hotSpotAddRequest) {
        HotSpotResponse hotSpotResponse = hotSpotService.updateHotSpot(id, hotSpotAddRequest);
        return ResponseEntity.ok(hotSpotResponse);
    }

    @DeleteMapping("/{id}") // 삭제
    public ResponseEntity<Void> deleteHotSpot(@PathVariable Long id) {
        hotSpotService.deleteHotSpot(id);
        return ResponseEntity.noContent().build();
    }
}