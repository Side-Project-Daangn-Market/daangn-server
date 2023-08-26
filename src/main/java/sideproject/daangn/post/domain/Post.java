package sideproject.daangn.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    //Constructor
    public Post(String title, int price, PostType postType, String content) {
        this.title = title;
        this.price = price;
        this.postType = postType;
        this.content = content;

        if(postType==PostType.SHARE)
            this.postStatus=PostStatus.ON_SHARE;
        else
            this.postStatus=PostStatus.ON_SELL;

        this.postTime= LocalDateTime.now();
    }
    //field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int price;
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
    private LocalDateTime postTime;
    @Lob
    private String content;
}
