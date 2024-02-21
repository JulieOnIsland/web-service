package com.juhyun.book.webservice.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After("") // Junit에서 단위테스트가 끝날 때마다 수행되는 메서드 지정
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void getArticles() {
        String title = "test title";
        String content = "test content";

        // save: id 값이 있다면 Update, 없다면 Insert 쿼리 실행
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("juhyun")
                        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
