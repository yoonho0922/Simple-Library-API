package com.project.simplelibraryapi.domain.response;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record BookSearchResponse(
    String title,
    String author,
    String publisher,
    LocalDate pubDate
) {

}
