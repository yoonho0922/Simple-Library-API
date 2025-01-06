package com.project.simplelibraryapi.repository;

import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;

public interface BookRepository {

    PageResult<BookSearchResponse> searchBooks(String query, Integer page, Integer size);
}
