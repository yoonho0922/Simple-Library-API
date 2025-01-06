package com.project.simplelibraryapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.simplelibraryapi.IntegrationTest;
import com.project.simplelibraryapi.controller.request.BookSearchRequest;
import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Disabled
class BookControllerIntegrationTest extends IntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(BookControllerIntegrationTest.class);

    @Autowired
    private BookController bookController;

    @Test
    @DisplayName("도서 검색 API 테스트")
    public void searchBooks(){
        //given
        BookSearchRequest request = new BookSearchRequest("스프링", 1, 10);

        //when
        PageResult<BookSearchResponse> response = bookController.searchBooks(request);

        //then
        assertEquals(request.getPage(), response.page());
        assertEquals(request.getSize(), response.size());
        assertThat(response.contents().get(0).title()).contains(request.getSearchWord());

        log.info("Response: {}", response);
    }

}