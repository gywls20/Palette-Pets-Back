package com.palette.palettepetsback.member.repository;

import com.palette.palettepetsback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {

    Member findByPassword(String password);
}
