package com.project.simplelibraryapi.service;

import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import com.project.simplelibraryapi.repository.NaverBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final NaverBookRepository naverBookRepository;

    public PageResult<BookSearchResponse> searchBooks(String searchWord, Integer page, Integer size) {
        return naverBookRepository.searchBooks(searchWord, page, size);
    }
}
