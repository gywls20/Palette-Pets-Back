package com.palette.palettepetsback.config.aop.notification;

import com.palette.palettepetsback.notification.service.MemberIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Aspect
@Component
//@EnableAsync
@RequiredArgsConstructor
public class NotificationAspect {

    private final MemberIssueService memberIssueService;

    @Pointcut("@annotation(com.palette.palettepetsback.config.aop.notification.NeedNotification)")
    public void annotationPointcut() {
    }

//    @Async
    @AfterReturning(pointcut = "annotationPointcut()", returning = "result")
    public void sendNotification(JoinPoint joinPoint, Object result) throws Throwable {

        long receiverId = NotificationThreadLocal.getReceiverId();
        String issueContent = NotificationThreadLocal.getIssueContent();
        int issueCode = NotificationThreadLocal.getIssueCode();

        log.info("receiverId: {}, issueContent: {}, issueCode = {}", receiverId, issueContent, issueCode);
        log.info("result = '{}'", result);

        memberIssueService.sendNotification(receiverId, issueContent, issueCode);
    }
}
