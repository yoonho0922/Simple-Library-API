package com.project.simplelibraryapi.client.book;

import java.util.List;
import lombok.Builder;

@Builder
public record NaverBookResponse(
    String lastBuildDate,
    int total,
    int start,
    int display,
    List<NaverBookItem> items
) {

}
