package com.kumugu.myblog.service;

import com.kumugu.myblog.domain.BlogPost;
import com.kumugu.myblog.domain.dto.BlogPostRequestDto;
import com.kumugu.myblog.domain.dto.BlogPostResponseDto;
import com.kumugu.myblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    // 게시글 작성
    public BlogPostResponseDto createBlogPost(BlogPostRequestDto blogPostRequestDto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostRequestDto.getTitle());
        blogPost.setContent(blogPostRequestDto.getContent());
        blogPost.setAuthor(blogPostRequestDto.getAuthor());
        BlogPost savedPost = blogPostRepository.save(blogPost);
        return new BlogPostResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getAuthor());
    }

    // 게시글 전체 조회
    public List<BlogPostResponseDto> getAllBlogPosts() {
        return blogPostRepository.findAll().stream()
                .map(post -> new BlogPostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor()))
                .collect(Collectors.toList());
    }

    // 특정 게시글 조회
    public Optional<BlogPostResponseDto> getBlogPostById(Long id) {
        return blogPostRepository.findById(id)
                .map(post -> new BlogPostResponseDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor()));
    }

    // 게시글 수정
    public Optional<BlogPostResponseDto> updateBlogPost(Long id, BlogPostRequestDto updateBlogPost) {
        return blogPostRepository.findById(id).map(existingPost -> {
            existingPost.setTitle(updateBlogPost.getTitle());
            existingPost.setContent(updateBlogPost.getContent());
            existingPost.setAuthor(updateBlogPost.getAuthor());
            BlogPost savedPost = blogPostRepository.save(existingPost);
            return new BlogPostResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getAuthor());
        });
    }

    // 게시글 삭제
    public boolean deleteBlogPost(Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
