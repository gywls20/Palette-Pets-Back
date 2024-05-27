package com.palette.palettepetsback;

import com.palette.palettepetsback.member.dto.Role;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PalettePetsBackApplication {

    // 테스트용임 -> 곧 지워야 함
    private final MemberRepository memberRepository;

    public PalettePetsBackApplication(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PalettePetsBackApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        memberRepository.save(
//                Member.builder()
//                        .password("1234")
//                        .role(Role.USER)
//                        .build()
//        );
//    }
}
