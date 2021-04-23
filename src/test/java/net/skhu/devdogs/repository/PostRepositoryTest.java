package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.entity.PostCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void saveAndFind() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .build();
        Post savePost = postRepository.save(post);
        Post findPost = postRepository.findById(post.getId()).get();

        assertThat(findPost.getId()).isEqualTo(post.getId());
    }

    @Test
    public void update() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .build();
        postRepository.save(post);
        Post findPost = postRepository.findById(post.getId()).get();
        String updateTitle = "update Hello";
        String updateContent = " update World";
        findPost.postUpdate(updateTitle, updateContent, null);
        em.flush();
        em.clear();
        Post updatePost = postRepository.findById(findPost.getId()).get();

        assertThat(updatePost.getTitle()).isEqualTo(updateTitle);
        assertThat(updatePost.getContent()).isEqualTo(updateContent);
    }

    @Test
    public void delete() {
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .build();
        postRepository.save(post);
        postRepository.delete(post);
        assertThrows(NoSuchElementException.class, () -> {
            postRepository.findById(post.getId()).get().getTitle();
        });
    }

    @Test
    public void findByCategoryId() {
        List<Post> posts = postRepository.findByCategoryId(1L);

        PostCategory postCategory = posts.get(0).getPostCategory();

        assertThat(postCategory.getId()).isEqualTo(1L);

    }
}
