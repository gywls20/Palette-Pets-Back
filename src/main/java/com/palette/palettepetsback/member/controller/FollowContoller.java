package com.palette.palettepetsback.member.controller;

import com.palette.palettepetsback.config.security.CustomUserDetails;
import com.palette.palettepetsback.member.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowContoller {
    private final FollowService followService;


    //팔로우 버튼
    //팔로우 할 유저 닉네임 , 내 아이디
//    @PostMapping("member/follow")
//    public ResponseEntity<String> follow (@Valid @RequestBody FollowRequest followRequest){
//
//        // SecurityContext에서 인증 정보 추출
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getPrincipal() instanceof CustomUserDetails) {
//            Long memberId = getMemberId(authentication);
//
//        } else {
//            return ResponseEntity.badRequest().body("인증되지 않은 사용자입니다.");
//        }
//
//        return null;
//    }

    private static Long getMemberId(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMember().getMemberId();
        return memberId;
    }

    @PostMapping("member/{nickname}")
    public ResponseEntity<String> follow(@PathVariable("nickname") String nickname) {
        
        // SecurityContext에서 인증 정보 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            Long memberId = getMemberId(authentication);
            followService.follow(nickname, memberId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("인증되지 않은 사용자입니다.");
        }

    }

    @DeleteMapping("member/{nickname}")
    public ResponseEntity<String> unfollow(@PathVariable("nickname") String nickname) {
        // SecurityContext에서 인증 정보 추출
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            Long memberId = getMemberId(authentication);
            followService.unfollow(nickname, memberId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("인증되지 않은 사용자입니다.");
        }
    }
    @GetMapping("/member/{nickname}")
    public ResponseEntity<String> getFollowers(@PathVariable("nickname") String nickname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            Long memberId = getMemberId(authentication);
            followService.getFollowers(memberId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("인증되지 않은 사용자입니다.");
        }

    }


//    @GetMapping("/followers/{memberId}")
//    public ResponseEntity<List<MemberDTO>> getFollowers(@PathVariable Long memberId) {
//        List<Member> followers = followService.getFollowers(memberId);
//        List<MemberDTO> followerDTOs = followers.stream()
//                .map(MemberDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(followerDTOs);
//    }
//
//    @GetMapping("/following/{memberId}")
//    public ResponseEntity<List<MemberDTO>> getFollowing(@PathVariable Long memberId) {
//        List<Member> following = followService.getFollowing(memberId);
//        List<MemberDTO> followingDTOs = following.stream()
//                .map(MemberDTO::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(followingDTOs);
//    }

}
