package com.juhyun.book.webservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class) // JUnit 5
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈 주입
    private MockMvc mvc; // 웹 API 테스트할 때 사용.

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // 체이닝 지원
                .andExpect(status().isOk()) // 결과 검증, Ok는 200인지 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1999;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json 응답값을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
