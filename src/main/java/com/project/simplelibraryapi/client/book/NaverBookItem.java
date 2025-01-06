package com.project.simplelibraryapi.client.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class NaverBookItem {
    private String title;
    private String link;
    private String image;
    private String author;
    private String discount;
    private String publisher;
    @JsonProperty("pubdate")
    private String pubDate;
    private String isbn;
    private String description;
}
