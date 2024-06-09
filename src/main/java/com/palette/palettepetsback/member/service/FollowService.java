package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.entity.Follow;
import com.palette.palettepetsback.member.repository.FollowRepository;
import com.palette.palettepetsback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    public void follow(String nickname, Long followeeId) {
        Optional<Member> optionalMember = memberRepository.findByMemberNickname(nickname);
        if (optionalMember.isPresent()) {
            Member follower = optionalMember.get();
            Member followee = memberRepository.findById(followeeId).orElseThrow();

            Follow follow = new Follow();
            follow.setFollowerId(follower);
            follow.setFolloweeId(followee);
            followRepository.save(follow);
        } else {

        }
    }

    public void unfollow(String nickname, Long followeeId) {
        Optional<Member> followerMember = memberRepository.findByMemberNickname(nickname);
        Optional<Member> followeeMember = memberRepository.findByMemberId(followeeId);

        Follow follow = followRepository.findByFollowerIdAndFolloweeId(followerMember.get(), followeeMember.get());
        followRepository.delete(follow);
    }

    public List<Member> getFollowers(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        System.out.println("member = " + member);

        System.out.println("member.getFollowerList() = " + member.getFollowerList());
        System.out.println("member.getFollowerList().get(0) = " + member.getFollowerList().get(0));
        for (Follow follower : member.getFollowerList()) {
            System.out.println("follower.getMemberFollowId() = " + follower.getMemberFollowId());
            System.out.println("follower.getFollowerId() = " + follower.getFollowerId());
            System.out.println("follower.getFolloweeId() = " + follower.getFolloweeId());
            System.out.println("follower.getTime() = " + follower.getTime());
        }
        return member.getFollowerList().stream()
                .map(Follow::getFollowerId)
                .collect(Collectors.toList());
    }

    public List<Member> getFollowing(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return member.getFollowingList().stream()
                .map(Follow::getFolloweeId)
                .collect(Collectors.toList());
    }


    //팔로우 하기
    // 닉네임을 가쟈오면 아이디로 저장 , 내 아이디 저장
//    public void follow(String followName, Long memberId){
//        //닉네임으로 아이디 찾기
//        Optional<Member> optionalMember = memberRepository.findByMemberNickname(followName);
//        Follow Follow = new Follow();
//        if(optionalMember.isPresent()){
//            Follow.saveFollow(memberId, optionalMember.get().getMemberId());
//        }
//
//    }

    //사용자 아이디로 팔로우 팔로워 수 구하기
    public void getFollow() {

    }

    //피드 저장

    //피드 삭제


}
