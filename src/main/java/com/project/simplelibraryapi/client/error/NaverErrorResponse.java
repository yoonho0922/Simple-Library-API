package com.project.simplelibraryapi.client.error;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverErrorResponse {
    private String errorMessage;
    private String errorCode;
}
