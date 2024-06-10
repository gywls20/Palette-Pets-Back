package com.palette.palettepetsback.Article.articleWrite.service;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class LocalFileService implements FileService{



    @Override
    public void upload(MultipartFile file, String filename) {

    }

    @Override
    public void delete(String filename) {

    }
}
