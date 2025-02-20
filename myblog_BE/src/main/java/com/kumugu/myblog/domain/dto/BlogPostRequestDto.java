package com.kumugu.myblog.domain.dto;

import com.kumugu.myblog.domain.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogPostRequestDto {
    private String title;
    private String content;
    private String author;
}
