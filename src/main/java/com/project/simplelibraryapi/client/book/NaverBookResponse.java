package com.project.simplelibraryapi.client.book;

import java.util.List;
import lombok.Getter;

@Getter
public class NaverBookResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBookItem> items;
}
