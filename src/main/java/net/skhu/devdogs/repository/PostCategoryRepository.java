package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

    public PostCategory findByName(String name);

}
