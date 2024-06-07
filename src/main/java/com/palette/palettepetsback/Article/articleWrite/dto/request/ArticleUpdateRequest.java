package com.palette.palettepetsback.Article.articleWrite.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateRequest {

    @NotBlank(message="게시글 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String content;


    //addedImages만들어야함
        private List<MultipartFile> addedImages = new ArrayList<>();


    private List<Integer> deletedImages = new ArrayList<>();

}
