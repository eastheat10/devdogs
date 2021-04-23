package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.postCategory.id = :category_id")
    public List<Post> findByCategoryId(@Param("category_id") Long id);

}
