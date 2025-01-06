package com.project.simplelibraryapi.domain.response;

import java.util.List;
import lombok.Builder;

@Builder
public record PageResult<T>(
    int page,
    int size,
    int totalElements,
    List<T> contents
) {

}

