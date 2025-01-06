package com.project.simplelibraryapi.client.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record NaverBookItem(
    String title,
    String link,
    String image,
    String author,
    String discount,
    String publisher,
    @JsonProperty("pubdate")
    String pubDate,
    String isbn,
    String description
) {

}
