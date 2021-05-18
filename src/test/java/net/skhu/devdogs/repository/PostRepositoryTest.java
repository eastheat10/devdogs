package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.entity.PostCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostCategoryRepository postCategoryRepository;

    @PersistenceContext
    EntityManager em;

    public Member createMember() {
        Member member = Member.builder()
                .name("윤동열")
                .studentId("201733027")
                .password("1234")
                .phoneNumber("01051791813")
                .joinDate(LocalDate.of(2021, 3, 2))
                .build();
        em.persist(member); em.flush();
        return member;
    }

    @Test
    public void findPostCategory() {
        List<PostCategory> list = postCategoryRepository.findAll();
        assertThat(list.size()).isEqualTo(4);
    }


    @Test
    public void saveAndFind() {
        Member member = createMember();
        PostCategory pc = postCategoryRepository.findByName("active");
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .member(member)
                .postCategory(pc)
                .build();
        Post findPost = postRepository.findById(post.getId()).get();

        assertThat(findPost.getId()).isEqualTo(post.getId());
    }

    @Test
    public void update() {
        Member member = createMember();
        PostCategory pc = postCategoryRepository.findByName("project");
        PostCategory updatePc = postCategoryRepository.findByName("free");
        Post post = Post.builder()
                .title("hello")
                .content("world")
                .member(member)
                .postCategory(pc)
                .build();
        postRepository.save(post);
        Post findPost = postRepository.findById(post.getId()).get();
        String updateTitle = "update Hello";
        String updateContent = " update World";
        findPost.postUpdate(updateTitle, updateContent, updatePc);
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
