package com.project.simplelibraryapi.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.simplelibraryapi.IntegrationTest;
import com.project.simplelibraryapi.client.book.NaverBookResponse;
import com.project.simplelibraryapi.common.exception.ApiException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Disabled
class NaverClientIntegrationTest extends IntegrationTest {

    @Autowired
    NaverClient naverClient;

    @Test
    @DisplayName("naver 책 검색")
    public void getNaverBooks(){
        //given
        String query = "스프링";
        int start = 1;
        int display = 10;

        //when
        NaverBookResponse response = naverClient.getBooks(query, start, display);

        //then
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertFalse(response.getItems().isEmpty());
        assertEquals(display, response.getItems().size());
    }

    @Test
    @DisplayName("naver 책 검색 오류 발생")
    public void test(){
        //given
        String query = "스프링";
        int start = 1;
        int display = 10000;

        //when
        ApiException exception = assertThrows(ApiException.class, () -> {
            naverClient.getBooks(query, start, display);
        });

        //then
        assertEquals("Invalid display value (부적절한 display 값입니다.)", exception.getErrorMessage());
    }
}