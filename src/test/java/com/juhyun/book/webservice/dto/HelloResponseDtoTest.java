package com.juhyun.book.webservice.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void hello_lombok_test() {

        String name = "juhyun";
        int amount = 1999;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertThat: 검증하고 싶은 대상을 메서드 인자로 받는다.
        // Junit assertThat 보다  assertj의 assertThat이 CoreMatchers와 같은 라이브러리가 필요하지 않고
        // 자동완성이 더 확실하게 지원된다


    }
}
