package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.entity.Member;
import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.entity.PostCategory;
import net.skhu.devdogs.repository.MemberRepository;
import net.skhu.devdogs.repository.PostCategoryRepository;
import net.skhu.devdogs.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostCategoryRepository postCategoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long write(PostDto postDto, MemberDto memberDto) {
        Member writer = memberRepository.findByStudentId(memberDto.getStudentId());
        Post post = toEntity(postDto);
        post.setWriter(writer);
        Post savePost = postRepository.save(post);
        return savePost.getId();
    }

    public List<PostDto> findAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = new PostDto(post);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).get();
        PostDto findPostDto = new PostDto(post);
        return findPostDto;
    }

    public List<PostDto> findByPostCategory(Long id) {
        List<Post> posts = postRepository.findByCategoryId(id);
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts) {
            PostDto findPostDto = new PostDto(post);
            postDtoList.add(findPostDto);
        }
        return postDtoList;
    }

    @Transactional
    public Long update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        PostCategory findPostCategory = postCategoryRepository.findById(postDto.getPostCategoryId()).get();
        post.postUpdate(postDto.getTitle(), postDto.getContent(), findPostCategory);
        return post.getId();
    }

    @Transactional
    public void delete(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        postRepository.delete(post);
    }

    public Post toEntity(PostDto postDto) {
        PostCategory findPostCategory = postCategoryRepository.findById(postDto.getPostCategoryId()).get();
        Post build = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postCategory(findPostCategory)
                .build();
        return build;
    }

}
