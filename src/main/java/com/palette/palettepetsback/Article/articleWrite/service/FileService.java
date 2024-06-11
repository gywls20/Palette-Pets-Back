package com.palette.palettepetsback.Article.articleWrite.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    void upload(MultipartFile file, String filename);

    void delete(String filename);
}
