package net.skhu.devdogs.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class PostTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName(value = "게시물 작성 / 조회")
    public void write() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .postType(PostType.FREE)
                .build();
        em.persist(post);   // persist 이후 기본키 매핑됨
        em.flush();
        em.clear();

        Post findPost = em.find(Post.class, post.getId());

        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());

    }

    @Test
    @DisplayName("게시물 수정")
    public void update1() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .postType(PostType.FREE)
                .build();

        String updateTitle = "Update Title";
        String updateContent = "Update Content";

        post.postUpdate(updateTitle, updateContent);
        assertThat(post.getTitle()).isEqualTo(updateTitle);
        assertThat(post.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("게시물 수정_EntityManager 사용")
    public void update2() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .postType(PostType.FREE)
                .build();
        em.persist(post);
        em.flush();
        em.clear();

        Post findPost = em.find(Post.class, post.getId());
        String updateTitle = "Update Title";
        String updateContent = "Update Content";

        findPost.postUpdate(updateTitle, updateContent);
        System.out.println("before flush");
        em.flush(); // update 쿼리 생성
        em.clear();
        System.out.println("after flush");

        Post updatePost = em.find(Post.class, findPost.getId());

        assertThat(updatePost.getTitle()).isEqualTo(updateTitle);
        assertThat(updatePost.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("게시물 삭제")
    public void delete() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .postType(PostType.FREE)
                .build();
        em.persist(post);
        em.flush();
        em.clear();

        Post findPost = em.find(Post.class, post.getId());
        em.remove(findPost);

        System.out.println("before flush");
        em.flush(); // delete 쿼리 생성
        em.clear();
        System.out.println("after flush");

        Post deletePost = em.find(Post.class, findPost.getId());
        assertThrows(NullPointerException.class, () -> {
            assertThat(deletePost.getId()).isEqualTo(findPost.getId());
        });
    }
}