package com.project.simplelibraryapi.repository;

import com.project.simplelibraryapi.client.NaverClient;
import com.project.simplelibraryapi.client.book.NaverBookResponse;
import com.project.simplelibraryapi.common.util.Utils;
import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NaverBookRepository implements BookRepository {

    private final NaverClient naverClient;

    @Override
    public PageResult<BookSearchResponse> searchBooks(String query, Integer page, Integer size) {
        NaverBookResponse externalResponse = naverClient.getBooks(query, page, size);
        List<BookSearchResponse> contents = externalResponse.items().stream().map(item -> BookSearchResponse.builder()
            .title(item.title())
            .author(item.author())
            .publisher(item.publisher())
            .pubDate(Utils.parseYYYYMMDD(item.pubDate()))
            .build()
        ).toList();

        return new PageResult<>(page, size, externalResponse.total(), contents);
    }
}
