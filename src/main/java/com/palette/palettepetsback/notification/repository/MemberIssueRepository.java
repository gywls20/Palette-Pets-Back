package com.palette.palettepetsback.notification.repository;

import com.palette.palettepetsback.notification.domain.MemberIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberIssueRepository extends JpaRepository<MemberIssue, Long> {
}
