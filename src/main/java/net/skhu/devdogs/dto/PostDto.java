package net.skhu.devdogs.dto;


import lombok.*;
import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.entity.PostType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class PostDto {

    private Long id;
    private String writer;
    private String title;
    private String content;

    private PostType postType;

    public Post toEntity() {
        Post post = Post.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .postType(postType)
                .build();
        return post;
    }

    @Builder
    public PostDto(String writer, String title, String content, PostType postType) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.postType = postType;
    }

    public PostDto(Post post) {
        id = post.getId();
        writer = post.getWriter();
        title = post.getTitle();
        content = post.getContent();
        postType = getPostType();
    }

}
