package com.project.simplelibraryapi.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookSearchRequest {

    @NotBlank(message = "필수 값 입니다")
    @Size(max = 50, message = "50자를 초과할 수 없습니다")
    private String searchWord;

    @NotNull(message = "필수 값 입니다")
    private Integer page;

    @NotNull(message = "필수 값 입니다")
    private Integer size;
}
