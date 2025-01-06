package com.project.simplelibraryapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.project.simplelibraryapi.client.NaverClient;
import com.project.simplelibraryapi.client.book.NaverBookItem;
import com.project.simplelibraryapi.client.book.NaverBookResponse;
import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NaverBookRepositoryTest {

    @Mock
    private NaverClient naverClient;

    @InjectMocks
    private NaverBookRepository naverBookRepository;

    @Test
    @DisplayName("외부 API 응답의 형식을 변환하여 제공")
    public void test() {
        //given
        String query = "스프링";
        int page = 1;
        int size = 10;

        List<NaverBookItem> mockItems = List.of(
            NaverBookItem.builder()
                .title("토비의 스프링")
                .author("토비")
                .publisher("한빛")
                .pubDate("20250101")
                .build(),
            NaverBookItem.builder()
                .title("스프링 인 액션")
                .author("크레이그월즈")
                .publisher("제이펍")
                .pubDate("20240101")
                .build()
            );

        when(naverClient.getBooks(query, page, size)).thenReturn(NaverBookResponse.builder()
            .total(2)
            .start(1)
            .display(10)
            .items(mockItems)
            .build());

        //when
        PageResult<BookSearchResponse> response = naverBookRepository.searchBooks(query, page, size);

        //then

        for (int i = 0; i < mockItems.size(); i++) {
            DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

            assertEquals(mockItems.get(i).title(), response.contents().get(i).title());
            assertEquals(mockItems.get(i).author(), response.contents().get(i).author());
            assertEquals(mockItems.get(i).publisher(), response.contents().get(i).publisher());
            assertEquals(LocalDate.parse(mockItems.get(i).pubDate(), YYYYMMDD_FORMATTER), response.contents().get(i).pubDate());
        }

        assertEquals(1, response.page());
        assertEquals(10, response.size());
        assertEquals(2, response.totalElements());
    }
}