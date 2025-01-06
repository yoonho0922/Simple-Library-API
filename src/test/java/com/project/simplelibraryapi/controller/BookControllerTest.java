package com.project.simplelibraryapi.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.project.simplelibraryapi.MvcTest;
import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import com.project.simplelibraryapi.service.BookService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


class BookControllerTest extends MvcTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private BookController bookController;

    @Test
    @DisplayName("도서 검색 정상 요청하면 정상 응답")
    public void searchBooks() throws Exception {
        //given
        String searchWord = "스프링";
        int page = 1;
        int size = 10;

        given(bookService.searchBooks(searchWord, page, size))
            .willReturn(new PageResult<>(1, 10, 10, List.of(Mockito.mock(BookSearchResponse.class))));

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
            .param("searchWord", searchWord)
            .param("page", String.valueOf(page))
            .param("size", String.valueOf(size)));

        //then
        result.andExpect(status().is2xxSuccessful())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("도서 검색 필수값 누락하여 요청하면 BadRequest 응답")
    public void searchBadRequest() throws Exception {
        //given
        String searchWord = "스프링";

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
            .param("searchWord", searchWord)
            .param("page", "1"));

        //then
        result.andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value("size-필수 값 입니다."));
    }

    @Test
    @DisplayName("도서 검색 키워드 50자 초과하면 BadRequest 응답")
    public void searchMaxKeyword() throws Exception {
        //given
        String searchWord = "10글자입니다람쥐.10글자입니다람쥐.10글자입니다람쥐.10글자입니다람쥐.10글자입니다람쥐.+";

        //when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/books")
            .param("searchWord", searchWord)
            .param("page", "1")
            .param("size", "10"));

        //then
        result.andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value("searchWord-50자를 초과할 수 없습니다."));
    }
}