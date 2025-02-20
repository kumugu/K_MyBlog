package com.kumugu.myblog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogPostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
}
