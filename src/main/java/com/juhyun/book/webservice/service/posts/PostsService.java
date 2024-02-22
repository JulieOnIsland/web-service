package com.juhyun.book.webservice.service.posts;

import com.juhyun.book.webservice.domain.posts.Posts;
import com.juhyun.book.webservice.domain.posts.PostsRepository;
import com.juhyun.book.webservice.dto.PostsListResponseDto;
import com.juhyun.book.webservice.dto.PostsResponseDto;
import com.juhyun.book.webservice.dto.PostsSaveRequestDto;
import com.juhyun.book.webservice.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 생성자로 빈을 주입받는 방식
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent()); // 더티 체킹
        // JPA의 엔티티 매니저가 활성화된 상태로 (Spring Data JPA를 쓰면 기본 옵션)
        // 트랜잭션 안에서 데이터베이스 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태.
        // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영.
        // 다시 말해, 엔티티의 값만 변경하면 별도로 Update 쿼리를 날릴 필요 없음.

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readOnly = true : 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도 개선.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(Posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }

}
