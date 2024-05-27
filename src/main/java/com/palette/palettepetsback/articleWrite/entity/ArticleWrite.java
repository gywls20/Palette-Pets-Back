package com.palette.palettepetsback.articleWrite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor //모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 매개변수가 아예 없는 기본 생성자 자동 생성
@Getter //각 필드 값을 조회할 수 있는 getter메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@Entity
@Table(name="article")
@Builder
public class ArticleWrite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 생성
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "created_who")
    private Long createdWho;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content",nullable=false)
    private String content;

    @Column(name = "article_tags")
    private String articleTags; //게시판 태그

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ArticleWrite.State state; //[active,delete]

    @Column(name = "count_loves")
    private int countLoves;

    @Column (name="count_report")
    private int countReport;

    @Column (name="count_views")
    private int countViews;

    @Column (name="count_review")
    private int countReview;

    @Column(name="is_deleted" ,nullable = false)
    private boolean isDeleted=false;




    @PrePersist
    private void setDefault(){
        this.countLoves = 0;
        this.countReport= 0;
        this.countViews = 0;
        this.countReview= 0;
        this.createdAt=LocalDateTime.now();
        this.state = ArticleWrite.State.ACTIVE;

    }
    public void markAsDeleted(){
        this.state = ArticleWrite.State.DELETED;
        this.isDeleted=true;
        this.createdAt=LocalDateTime.now();
    }

    @PreUpdate //사용하므로 수정할때마다 현재시간으로 시간이 저장
    protected void onUpdate(){
        if(this.state != ArticleWrite.State.DELETED) {
            createdAt = LocalDateTime.now();
            state = ArticleWrite.State.MODIFIED;
        }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setState(String modified) {
        this.state= ArticleWrite.State.valueOf(modified);
    }




    public enum State{
        ACTIVE,MODIFIED,DELETED
    }

    //Patch
    public void patch(ArticleWrite articleWrite) {
        if(articleWrite.title !=null)
            this.title = articleWrite.title;
        if(articleWrite.content != null)
            this.content = articleWrite.content;
        if(articleWrite.articleTags !=null)
            this.articleTags=articleWrite.articleTags;

    }
}
