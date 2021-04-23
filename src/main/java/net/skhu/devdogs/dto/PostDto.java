package net.skhu.devdogs.dto;


import lombok.*;
import net.skhu.devdogs.entity.Post;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class PostDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    private Long postCategoryId;

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
    }

}
