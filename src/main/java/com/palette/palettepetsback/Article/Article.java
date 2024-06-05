package com.palette.palettepetsback.Article;

import com.palette.palettepetsback.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long articleId;

    @Column(name="created_who")
    private Long createdWho;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

//    @Column(name = "article_tags")
//    private String articleTags;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private Article.State state;

    @Column(name = "count_loves")
    private Integer countLoves;

    @Column(name = "count_report")
    private Integer countReport;

    @Column(name = "count_views")
    private Integer countViews;

    @Column(name = "count_review")
    private Integer countReview;

    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted;

    //추가 Entity
    @Column(name="board_name")
    @Enumerated(EnumType.STRING)
    private Article.ComminityBoard boardName;

    @Column(name="article_head")
    private String articleHead;



    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "created_who", referencedColumnName = "member_id",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Member member;

    @OneToMany(mappedBy = "article",cascade=CascadeType.PERSIST,orphanRemoval = true)
    private List<ArticleImage>images;

    @PrePersist //Entity 실행 전 수행하는 마라미터로 default 값을 지정O
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.countLoves = 0;
        this.countReport = 0;
        this.countReview = 0;
        this.countViews = 0;
        this.isDeleted = false;
        this.state = State.ACTIVE;
    }

    public void markAsDeleted(){
        this.state = State.DELETED;
        this.isDeleted=true;
        this.createdAt=LocalDateTime.now();
    }

    @PreUpdate //사용하므로 수정할때마다 현재시간으로 시간이 저장
    protected void onUpdate(){
        if(this.state != State.DELETED) {
            createdAt = LocalDateTime.now();
            state = State.MODIFIED;
        }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setState(String modified) {
        this.state= State.valueOf(modified);
    }

    public void setBoardName(String modified) {
        this.boardName= ComminityBoard.valueOf(modified);
    }

    public enum ComminityBoard{
        FREEBOARD,INFORMATION,SHOW,QNA
    }
    public enum State{
        ACTIVE,MODIFIED,DELETED
    }

    //Patch
    public void patch(Article article) {
        if(article.title !=null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;

    }
}
