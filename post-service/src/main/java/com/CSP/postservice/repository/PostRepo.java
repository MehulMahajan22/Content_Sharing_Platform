package com.CSP.postservice.repository;

import com.CSP.postservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "SELECT * FROM POST WHERE LOCATE( ?1 , HEADING ) > 0", nativeQuery = true)
    List<Post> findByHeading(String heading);

    @Query(value = "SELECT * FROM POST WHERE LOCATE( ?1 , CONTENT ) > 0", nativeQuery = true)
    List<Post> findByContent(String content);

    @Query(value = "SELECT * FROM POST WHERE OWNER_ID = ?1",nativeQuery = true)
    List<Post> findByOwnerId(int user_id);

}
