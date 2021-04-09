package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.dto.PostDto;
import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long write(PostDto postDto) {
        Post post = postDto.toEntity();
        postRepository.save(post);
        return post.getId();
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

    public List<PostDto> findByPostType(PostDto postDto) {
        List<Post> posts = postRepository.findByPostType(postDto.getPostType());
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : posts) {
            PostDto findPostDto = new PostDto(post);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    public Long update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        post.postUpdate(postDto.getTitle(), postDto.getContent());
        return post.getId();
    }

    public void delete(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        postRepository.delete(post);
    }
    
}
