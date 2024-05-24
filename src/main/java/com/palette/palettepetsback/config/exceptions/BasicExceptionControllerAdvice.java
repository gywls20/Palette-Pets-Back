package com.palette.palettepetsback.config.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(1)
public class BasicExceptionControllerAdvice {

    // 회원 / 펫을 찾지 못하는 에러가 발생하였을 때 반환할 에러 응답
    @ExceptionHandler({ NoMemberExistException.class, NoSuchPetException.class })
    public ResponseEntity<?> handleNoMemberExistException(NoMemberExistException e) {
        log.error("NoMemberExistException = {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
