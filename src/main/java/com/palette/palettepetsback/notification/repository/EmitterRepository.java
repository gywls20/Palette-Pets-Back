package com.palette.palettepetsback.notification.repository;

import com.palette.palettepetsback.notification.domain.MemberIssue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.metrics.buffering.StartupTimeline;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmitterRepository {
    // sseEmitter 객체 저장용
    private final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();
    // 이벤트 캐시 저장용 -> 클라이언트가 연결을 잃어도 이벤트 유실을 방지하기 위해 임시로 저장되는 데이터
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();


    public SseEmitter save(String eventId, SseEmitter emitter) {
        emitterMap.put(eventId, emitter);
        return emitter;
    }

    public void delete(String eventId) {
        emitterMap.remove(eventId);
    }

    public void deleteAllEmitterStartWithId(String memberId) {
        // 해당 회원과 관련된 모든 emitter를 지움
        emitterMap.forEach(
                (key, emitter) -> {
                    if (key.startsWith(memberId)) {
                        emitterMap.remove(key);
                    }
                }
        );
    }

    public void saveEventCache(String eventCacheId, Object event) {
        // 이벤트를 저장
        eventCache.put(eventCacheId, event);
    }

    public void deleteAllEventCacheStartWithId(String memberId) {
        // 해당 회원과 관련된 모든 이벤트를 지움
        eventCache.forEach(
                (key, emitter) -> {
                    if (key.startsWith(memberId)) {
                        eventCache.remove(key);
                    }
                }
        );
    }

    public Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String memberId) {
        // 해당 회원과 관련된 모든 이벤트를 찾음
        return emitterMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, SseEmitter> findAllEventCacheStartWithId(String memberId) {

        return emitterMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(memberId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
