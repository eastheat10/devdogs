package net.skhu.devdogs.repository;

import net.skhu.devdogs.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.postCategory.id = :categoryId order by p.modifiedDate desc")
    List<Post> findPostsByCategoryId(@Param("categoryId") Long id);

    @Query("select p from Post p where p.postCategory.id = :categoryId")
    Page<Post> findPostsByCategoryId(@Param("categoryId") Long id, Pageable pageable);

    @Query("select p from Post p where p.member.id = :memberId order by p.createTime")
    List<Post> findPostsByStudentId(@Param("memberId") Long memberId);

    @Query("select p from Post p where p.title like %:searchContent%")
    List<Post> findByTitleLike(@Param("searchContent") String searchContent);

    @Query("select p from Post p where p.content like %:searchContent%")
    List<Post> findByContentLike(@Param("searchContent") String searchContent);

    @Query("select p from Post p where p.member.name like %:searchContent%")
    List<Post> findByMemberLike(@Param("searchContent") String searchContent);

}
