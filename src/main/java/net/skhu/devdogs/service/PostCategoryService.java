package net.skhu.devdogs.service;

import lombok.RequiredArgsConstructor;
import net.skhu.devdogs.entity.PostCategory;
import net.skhu.devdogs.repository.PostCategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;

    public PostCategory findByName(String name) {
        PostCategory findPostCategory = postCategoryRepository.findByName(name);
        return findPostCategory;
    }
}
