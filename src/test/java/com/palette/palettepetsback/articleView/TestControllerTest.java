package com.palette.palettepetsback.articleView;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.fail;
import com.palette.palettepetsback.articleView.DTO.ArticleSaveDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") //test.yml 연결
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("테스트 기능을 테스트하는 것에 성공")
    @Test
    public void shouldReturnHelloWorld() throws Exception {
        //given
        final String url = "/api/hello";

        //when
        final ResultActions result = mockMvc.perform(get(url));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @DisplayName("쓰기 작업")
    @Test
    public void shouldSaveArticle() throws Exception {
        //given
        final String url = "/api/save/article";
        ArticleSaveDTO request = new ArticleSaveDTO("제목","내용", "태그");

        String requestJson = objectMapper.writeValueAsString(request);

        //when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType("application/json")
                .content(requestJson));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
}
