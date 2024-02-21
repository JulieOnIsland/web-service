package com.juhyun.book.webservice.service.posts;

import com.juhyun.book.webservice.domain.posts.PostsRepository;
import com.juhyun.book.webservice.dto.PostsSaveRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 생성자로 빈을 주입받는 방식
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
