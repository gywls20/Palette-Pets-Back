<<<<<<< HEAD
//package com.palette.palettepetsback.carrot;
//
//import com.palette.palettepetsback.carrot.domain.Carrot;
//import com.palette.palettepetsback.carrot.repository.CarrotImageRepository;
//import com.palette.palettepetsback.carrot.repository.CarrotRepository;
//import com.palette.palettepetsback.member.entity.Member;
//import com.palette.palettepetsback.member.repository.MemberRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//public class CarrotTest {
//
//    @Autowired
//    private CarrotRepository carrotRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private CarrotImageRepository carrotImageRepository;
//
//    @Test
//    void save() {
//        Optional<Member> member = memberRepository.findById(Long.valueOf(27));
//
//        Carrot save = Carrot.builder()
//                .member(member.get())
//                .carrotTitle("망고 산책 시켜주실 분 구해요")
//                .carrotContent("망고는 안물고 착한 강아지예요")
//                .carrotTag("산책")
//                .carrot_price(500000)
//                .build();
//        Carrot carrotDTO = carrotRepository.save(save);
//        Assertions.assertEquals(carrotDTO.getCarrotId(), 2);
//    }
//
//    @Test
//    void delete() {
//        Optional<Carrot> carrot = carrotRepository.findById(Long.valueOf(3));
//        carrotRepository.delete(carrot.get());
//
//    }
//
//    @Test
//    @Transactional
//    void findAllCarrot() {
//            List<Carrot> carrots = carrotRepository.findAll();
//            System.out.println(carrots);
//    }
//
//}
=======
package com.palette.palettepetsback.carrot;

import com.palette.palettepetsback.carrot.domain.Carrot;
import com.palette.palettepetsback.carrot.repository.CarrotImageRepository;
import com.palette.palettepetsback.carrot.repository.CarrotRepository;
import com.palette.palettepetsback.member.entity.Member;
import com.palette.palettepetsback.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CarrotTest {

    @Autowired
    private CarrotRepository carrotRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CarrotImageRepository carrotImageRepository;

    @Test
    void save() {
        Optional<Member> member = memberRepository.findById(Long.valueOf(24));

        Carrot save = Carrot.builder()
                .member(member.get())
                .carrotTitle("쵸파 미니 팬미팅 합니다")
                .carrotContent("말티즈계의 최고 얼짱 쵸파를 보러오세요")
                .carrotTag("산책")
                .carrot_price(0)
                .build();
        Carrot carrotDTO = carrotRepository.save(save);
        Assertions.assertEquals(carrotDTO.getCarrotId(), 3);
    }

    @Test
    void delete() {
        Optional<Carrot> carrot = carrotRepository.findById(Long.valueOf(3));
        carrotRepository.delete(carrot.get());

    }

    @Test
    @Transactional
    void findAllCarrot() {
            List<Carrot> carrots = carrotRepository.findAll();
            System.out.println(carrots);
    }

}
>>>>>>> 1ad976c293e2de3641e5004610f49a04b6f0aa33
