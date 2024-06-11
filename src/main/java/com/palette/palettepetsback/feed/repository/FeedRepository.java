package com.palette.palettepetsback.feed.repository;

import com.palette.palettepetsback.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed,Long> {
}
