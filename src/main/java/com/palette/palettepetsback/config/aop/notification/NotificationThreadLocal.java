package com.palette.palettepetsback.config.aop.notification;

public class NotificationThreadLocal {

    private static final ThreadLocal<Long> receiverIdThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> issueContentThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Integer> issueCodeThreadLocal = new ThreadLocal<>();

    public static void setNotificationInfo(Long receiverId, String issueContent, Integer issueCode) {
        setReceiverId(receiverId);
        setIssueContent(issueContent);
        setIssueCode(issueCode);
    }

    private static void setReceiverId(Long receiverId) {
        receiverIdThreadLocal.set(receiverId);
    }

    public static Long getReceiverId() {
        return receiverIdThreadLocal.get();
    }

    private static void setIssueContent(String issueContent) {
        issueContentThreadLocal.set(issueContent);
    }

    public static String getIssueContent() {
        return issueContentThreadLocal.get();
    }

    private static void setIssueCode(Integer issueCode) {
        issueCodeThreadLocal.set(issueCode);
    }

    public static Integer getIssueCode() {
        return issueCodeThreadLocal.get();
    }

    public static void clear() {
        receiverIdThreadLocal.remove();
        issueContentThreadLocal.remove();
        issueCodeThreadLocal.remove();
    }
}