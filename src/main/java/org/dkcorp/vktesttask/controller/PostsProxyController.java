package org.dkcorp.vktesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.CommentDto;
import org.dkcorp.vktesttask.dto.PostDto;
import org.dkcorp.vktesttask.service.PostsProxyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "Posts", description = "Operations related to posts")
public class PostsProxyController {
    private final PostsProxyService postsProxyService;

    @GetMapping
    @Operation(summary = "Fetch all posts", description = "Fetch all posts from service")
    public List<PostDto> getAllPosts() {
        return postsProxyService.getAllPosts();
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Fetch post", description = "Fetch post by its id")
    public PostDto getPostById(@PathVariable Long postId) {
        return postsProxyService.getPostById(postId);
    }

    @GetMapping("/{postId}/comments")
    @Operation(summary = "Fetch post comments", description = "Fetch all comments for the post")
    public List<CommentDto> getPostComments(@PathVariable Long postId) {
        return postsProxyService.getPostComments(postId);
    }
}
