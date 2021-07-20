package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.MemberDto;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.dto.SearchDto;
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
        PostCategory postCategory = postCategoryRepository.findById(postDto.getPostCategoryId()).get();
        postDto.setPostCategoryName(postCategory.getName());
        postDto.setPostCategoryKoName(postCategory.getKoName());
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

    public List<PostDto> findPostByStudentId(String studentId) {
        Member member = memberRepository.findByStudentId(studentId);
        List<Post> posts = postRepository.findPostsByStudentId(member.getId());
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
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    @Transactional
    public void postInit() {
        for (long i = 1; i <= 4; i++) {
            PostCategory postCategory = postCategoryRepository.findById(i).get();
            for(long j = 1; j <= 20; j++){
                Post post = Post.builder()
                        .title("hello " + (char)(j * i) + (char)(j + i))
                        .content("world" + (char)(j * i) + (char)(j + i))
                        .member(memberRepository.findById((long)(Math.random() * 5 + 1)).get())
                        .postCategory(postCategory)
                        .build();
                postRepository.save(post);
            }
        }
    }

    public List<PostDto> searchResult(SearchDto searchDto) {
        List<Post> posts = new ArrayList<>();
        List<PostDto> postDtoList = new ArrayList<>();
        String searchContent = searchDto.getSearchContent();
        switch (searchDto.getSearchType()) {
            case "writer" :
                posts = postRepository.findByMemberLike(searchContent);
                break;
            case "title" :
                posts = postRepository.findByTitleLike(searchContent);
                break;
            case "content" :
                posts = postRepository.findByContentLike(searchContent);
                break;
        }
        for (Post post : posts) {
            postDtoList.add(new PostDto(post));
        }
        return postDtoList;
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
