package com.project.simplelibraryapi.controller;

import com.project.simplelibraryapi.controller.request.BookSearchRequest;
import com.project.simplelibraryapi.domain.response.BookSearchResponse;
import com.project.simplelibraryapi.domain.response.PageResult;
import com.project.simplelibraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public PageResult<BookSearchResponse> searchBooks(@Valid BookSearchRequest request) {
        return bookService.searchBooks(request.getSearchWord(), request.getPage(), request.getSize());
    }
}
