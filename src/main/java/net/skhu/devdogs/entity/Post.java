package net.skhu.devdogs.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String writer;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    private PostType postType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String writer, String title, String content, PostType postType, Member member) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.member = member;
    }

    public void postUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
