package com.project.simplelibraryapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequest {

    private String searchWord;
    private Integer page;
    private Integer size;
}
