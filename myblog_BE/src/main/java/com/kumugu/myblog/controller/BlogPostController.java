package com.kumugu.myblog.controller;

import com.kumugu.myblog.domain.dto.BlogPostRequestDto;
import com.kumugu.myblog.domain.dto.BlogPostResponseDto;
import com.kumugu.myblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<BlogPostResponseDto> createPost(@RequestBody BlogPostRequestDto blogPostRequestDto) {
        BlogPostResponseDto createdPost = blogPostService.createBlogPost(blogPostRequestDto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<List<BlogPostResponseDto>> getAllPosts() {
        List<BlogPostResponseDto> posts = blogPostService.getAllBlogPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostResponseDto> getBlogPost(@PathVariable Long id) {
        Optional<BlogPostResponseDto> blogPost = blogPostService.getBlogPostById(id);
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BlogPostResponseDto> updateBlogPost(@PathVariable Long id, @RequestBody BlogPostRequestDto updatedPost) {
        Optional<BlogPostResponseDto> updatedBlogPost = blogPostService.updateBlogPost(id, updatedPost);
        return updatedBlogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        return blogPostService.deleteBlogPost(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
