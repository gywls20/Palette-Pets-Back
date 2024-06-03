package com.palette.palettepetsback.config.Redis;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class redisRepository {
    private static final Logger log = LoggerFactory.getLogger(redisRepository.class);
    private final RedisTemplate<String, Object> redisTemplate;

    // 모든 테스트 객체를 저장할 해시 키
    private final String HASH_KEY = "Test2";


    public List<Object> findAll() {
        // 해당 해시 키 안의 모든 값을 찾아 반환한다.
        log.info(redisTemplate.opsForHash().values(HASH_KEY).toString());
        return redisTemplate.opsForHash().values(HASH_KEY);
    }
    public void delete(String id) {
        // 해당 해시 키 안의 id 값을 삭제한다.
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }

}
