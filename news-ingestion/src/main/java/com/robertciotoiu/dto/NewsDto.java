package com.robertciotoiu.dto;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class NewsDto {
    private String title;
    private String content;
    private OffsetDateTime publishDate;
    private OffsetDateTime updateDate;
    private String imageUrl;
}
