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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;

    @Builder
    public Post(String title, String content, PostCategory postCategory, Member member) {
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
        this.member = member;
    }

    public void postUpdate(String title, String content, PostCategory postCategory) {
        this.title = title;
        this.content = content;
        this.postCategory = postCategory;
    }

    public void setWriter(Member member) {
        this.member = member;
    }

}
