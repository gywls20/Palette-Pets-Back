package com.palette.palettepetsback.member.service;

import com.palette.palettepetsback.member.dto.JoinRequest;
import com.palette.palettepetsback.member.dto.MemberRequest;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //일반 로그인
    public Member login(String email, String password) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            log.info("그런 사람 없습니다.");
            return null;
        }else {
            Member member1 = member.get();

            // passwordEncoder.matches를 사용하여 비밀번호 비교
            if(passwordEncoder.matches(password, member1.getPassword())){
                return member1;
            }
        }
        log.info("비번 잘못 쳤습니다.");
        return null;
    }
    //이메일 중복확인
    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }
    //닉네임 중복확인
    public boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByMemberNickname(nickname);
    }

    //회원가입
    public void join(JoinRequest req) {
        log.info("JoinRequest = ",req.getPassword());
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        log.info("encodedPassword = ", encodedPassword);
        memberRepository.save(req.toEntity(encodedPassword));

    }



    // 비밀번호 수정
    public void updatePassword(Long memberId, MemberRequest.Password passwordRequest) {
        // DTO에서 비밀번호와 비밀번호 확인이 일치하는지 검증
        if (!passwordRequest.getPassword().equals(passwordRequest.getCheckPassword())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        optionalMember.ifPresent(member -> {
            // 비밀번호 암호화
            member.updatePassword(passwordEncoder.encode(passwordRequest.getPassword()));

            memberRepository.save(member);
        });

    }

    // 닉네임 변경
    public void updateNickname(Long memberId, MemberRequest.Nickname nicknameRequest) {
        // memberId로 DB에서 해당 회원 정보 조회
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);

        optionalMember.ifPresent(member -> {
            // 비밀번호 암호화
            member.updateNickname(nicknameRequest.getNickName());

            memberRepository.save(member);
        });

    }

    // 주소지 입력
    public void updateAddress(Long memberId, MemberRequest.Address addressRequest) {
        // memberId로 DB에서 해당 회원 정보 조회
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        optionalMember.ifPresent(member -> {

            member.createNameAndPhoneAndAddress(
                    addressRequest.getName(),
                    addressRequest.getPhone(),
                    addressRequest.getAddress());

            memberRepository.save(member);
        });

    }

    // 프로필 이미지 설정
    public void updateProfileImage(String imagePath) {

    }

    // 생일, 성별 변경
    public void updateBirthGender(Long memberId, MemberRequest.BirthGender BirthGenderRequest) {
        // memberId로 DB에서 해당 회원 정보 조회
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberId);
        optionalMember.ifPresent(member -> {
            member.updateBirthGender(
                    BirthGenderRequest.getGender()
                    ,BirthGenderRequest.getBirth()
            );

            memberRepository.save(member);
        });
    }

}
