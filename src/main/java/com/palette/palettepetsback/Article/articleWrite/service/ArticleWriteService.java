package com.palette.palettepetsback.Article.articleWrite.service;

import com.palette.palettepetsback.Article.Article;
import com.palette.palettepetsback.Article.ArticleImage;
import com.palette.palettepetsback.Article.articleView.DTO.reponsse.ArticleResponseDTO;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleImageDto;
import com.palette.palettepetsback.Article.articleWrite.dto.request.ArticleWriteDto;

import com.palette.palettepetsback.Article.articleWrite.dto.response.ArticleWriteResponseDto;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleWriteRepository;
import com.palette.palettepetsback.Article.articleWrite.repository.ImgArticleRepository;
import com.palette.palettepetsback.Article.articleWrite.repository.ArticleLikeRepository;
import com.palette.palettepetsback.Article.exception.type.ArticleNotFoundException;
import com.palette.palettepetsback.config.SingleTon.Singleton;
import com.palette.palettepetsback.config.Storage.NCPObjectStorageService;
import com.palette.palettepetsback.config.jwt.AuthInfoDto;
import com.palette.palettepetsback.config.jwt.JWTUtil;
import com.palette.palettepetsback.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleWriteService {

    private final ArticleWriteRepository articleWriteRepository;
    private final NCPObjectStorageService objectStorageService;
    private final ImgArticleRepository imgArticleRepository;
    private final ArticleLikeRepository likeArticleRepository;


    //게시글 이미지 Object Storage 등록
    public String uploadArticleImage(String filePath, MultipartFile file){
        return objectStorageService.uploadFile(Singleton.S3_BUCKET_NAME,filePath,file);

    }

    //게시글 이미지 정보 DB 등록
    @Transactional
    public  Long  createImgArticle(ArticleImageDto dto) {

        Article article = articleWriteRepository.findById(dto.getArticleId()).orElseThrow(()->new IllegalArgumentException("article not found"));

        ArticleImage saved = imgArticleRepository.save(
                ArticleImage.builder()
                        .imgUrl(dto.getImgUrl())
                        .article(article)
                        .build()
        );
        return saved.getId();
    }

    //get
    public List<Article> index() {
        return articleWriteRepository.findAll();
    }

    @Transactional
    public Article create(ArticleWriteDto dto) {

        AuthInfoDto memberInfo = JWTUtil.getMemberInfo(); //토큰을 가져와서 멤버아이디 찾아내기
//        log.info(String.valueOf(memberInfo.getMemberId()));
        if(memberInfo == null) {
            return null;
        }

        dto.setCreatedWho(memberInfo.getMemberId());//dto를 받아와서 멤버아이디가 없으니까  위에꺼를 가져와서 넣기

        Article articleWrite = Article.builder()
                .createdWho(dto.getCreatedWho())
                .articleTags(dto.getArticleTags())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        if(articleWrite.getArticleId()!=null){
            return null;
        }
        return articleWriteRepository.save(articleWrite);
    }

    @Transactional
    public Article update(Long id, ArticleWriteDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Article articleWrite = Article.builder()
                .createdWho(dto.getCreatedWho())
                .title(dto.getTitle())
                .content(dto.getContent())
                .articleTags(dto.getArticleTags()   )
                .build();
        log.info("id:{}, articleWrite:{}", id, articleWrite.toString());

        // 2. 타깃 조회
        Article target = articleWriteRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if (target == null) {
            log.info("잘못된 요청! id:{}, articleWrite:{}", id, articleWrite.toString());
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 4. 업데이트하기
        target.patch(articleWrite);

        // 5 .상태 업데이트
        target.setState("MODIFIED");

        // 6. 수정된 엔티티 저장
        Article updated = articleWriteRepository.save(target); // 수정된 부분: target을 저장

        return updated; // 응답은 컨트롤러가 하므로 여기서는 수정된 데이터만 반환
    }

    //DELETE
    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleWriteRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        articleWriteRepository.delete(target);
        return target; //DB에서 삭제한 대상을 컨트롤러에 반환
    }

    // 게시글 이미지 삭제
    @Transactional
    public void deleteImgArticle(List<Long> imgIds) {

        for(Long imgId : imgIds){
            imgArticleRepository.deleteById(imgId);
        }
    }

    //게시글 단건 조회
    public ArticleWriteResponseDto findArticle(Long articleId) {
        Article article = articleWriteRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        Member member = article.getMember();
        return ArticleWriteResponseDto.toDto(article,member.getMemberNickname());
    }
}



