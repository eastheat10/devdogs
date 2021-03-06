package net.skhu.devdogs.dto;


import lombok.*;
import net.skhu.devdogs.entity.Post;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class PostDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    private Long postCategoryId;
    private String postCategoryName;
    private String postCategoryKoName;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PostDto(String writer, String title, String content, Long postCategoryId) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.postCategoryId = postCategoryId;
    }

    public PostDto(Post post) {
        id = post.getId();
        writer = post.getMember().getName();
        title = post.getTitle();
        content = post.getContent();
        postCategoryId = post.getPostCategory().getId();
        postCategoryName = post.getPostCategory().getName();
        postCategoryKoName = post.getPostCategory().getKoName();
        createDate = post.getCreateTime();
        modifiedDate = post.getModifiedDate();
    }

}
