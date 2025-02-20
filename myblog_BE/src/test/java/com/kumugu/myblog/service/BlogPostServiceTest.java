package com.kumugu.myblog.service;

import com.kumugu.myblog.domain.BlogPost;
import com.kumugu.myblog.domain.dto.BlogPostRequestDto;
import com.kumugu.myblog.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BlogPostServiceTest {

    @InjectMocks
    private BlogPostService blogPostService;

    @Mock
    private BlogPostRepository blogPostRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBlogPost() {
        BlogPostRequestDto requestDto = new BlogPostRequestDto("Test Title", "Test Content", "Test Author");
        requestDto.setTitle("Test Title");
        requestDto.setContent("Test Content");
        requestDto.setAuthor("Test Author");

        BlogPost savedBlogPost = new BlogPost();
        savedBlogPost.setId(1L);
        savedBlogPost.setTitle("Test Title");
        savedBlogPost.setContent("Test Content");
        savedBlogPost.setAuthor("Test Author");

        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(savedBlogPost);

        var responseDto = blogPostService.createBlogPost(requestDto);

        assertEquals(1L, responseDto.getId());
        assertEquals("Test Title", responseDto.getTitle());
        assertEquals("Test Content", responseDto.getContent());
        assertEquals("Test Author", responseDto.getAuthor());
    }

    @Test
    void getAllBlogPosts() {
        // 테스트 데이터를 추가하고, findAll() 메서드를 통해 반환값을 확인합니다.
    }

    @Test
    void getBlogPostById() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("Test Title");
        blogPost.setContent("Test Content");
        blogPost.setAuthor("Test Author");

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));

        var responseDto = blogPostService.getBlogPostById(1L);

        assertTrue(responseDto.isPresent());
        assertEquals("Test Title", responseDto.get().getTitle());
    }

    @Test
    void updateBlogPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("Original Title");
        blogPost.setContent("Original Content");
        blogPost.setAuthor("Original Author");

        BlogPostRequestDto requestDto = new BlogPostRequestDto();
        requestDto.setTitle("Updated Title");
        requestDto.setContent("Updated Content");
        requestDto.setAuthor("Updated Author");

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));
        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(blogPost);

        var responseDto = blogPostService.updateBlogPost(1L, requestDto);

        assertTrue(responseDto.isPresent());
        assertEquals("Updated Title", responseDto.get().getTitle());
        assertEquals("Updated Content", responseDto.get().getContent());
        assertEquals("Updated Author", responseDto.get().getAuthor());
    }

    @Test
    void deleteBlogPost() {
        when(blogPostRepository.existsById(1L)).thenReturn(true);

        boolean result = blogPostService.deleteBlogPost(1L);

        assertTrue(result);
    }
}
