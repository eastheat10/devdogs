package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Post;
import net.skhu.devdogs.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByPostType(PostType postType);

}
