package com.juhyun.book.webservice.domain.posts;

import com.juhyun.book.webservice.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입>

    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
