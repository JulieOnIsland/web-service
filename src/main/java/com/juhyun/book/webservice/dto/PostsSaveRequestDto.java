package com.juhyun.book.webservice.dto;

import com.juhyun.book.webservice.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    // 중요한 점은 Entity와 유사함에도 Dto 클래스를 따로 만들었다는 점이다
    // 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안 된다.
    // Entity는 DB Layer, Request/Response는 View Layer 이므로 이들을 철저히 분리시켜야 한다.

}
