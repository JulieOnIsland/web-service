package com.juhyun.book.webservice.domain.posts;


import jakarta.persistence.*;
import lombok.*;

@Getter
// Entity 클래스에는 절대 Setter 메서드를 만들지 않는다
@Builder
@AllArgsConstructor // @Builder를 쓰기 위해서는 @AllArgsConstructor 어노테이션이 필요함
@NoArgsConstructor // 롬복 어노테이션은 필수가 아니므로 위쪽에 배치
@Entity // 어노테이션 순서: 주요 어노테이션을 클래스에 가깝게
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String author;


}
