package com.palette.palettepetsback.config.exceptions;

import com.palette.palettepetsback.member.entity.Member;
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

    @ExceptionHandler({ NoMemberExistException.class })
    public ResponseEntity<?> handleNoMemberExistException(NoMemberExistException e) {
        log.error("NoMemberExistException = {}", e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
