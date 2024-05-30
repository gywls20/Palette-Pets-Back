package com.palette.palettepetsback.Article.articleWrite.controller;

import com.amazonaws.Response;
import com.palette.palettepetsback.Article.ArticleLike;
import com.palette.palettepetsback.Article.articleWrite.dto.response.ArticleLikeResponseDto;
import com.palette.palettepetsback.Article.articleWrite.service.ArticleLikeService;
import com.palette.palettepetsback.member.dto.UserDTO;
import com.palette.palettepetsback.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin //리액트에서 넘어올때 포트가 다르면 오류가 생기는걸 해결해줌
@Log4j2
@RequiredArgsConstructor
public class LikeController {


    }





