package org.dkcorp.vktesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingCommentDto;
import org.dkcorp.vktesttask.dto.request.IncomingPostDto;
import org.dkcorp.vktesttask.dto.response.CommentDto;
import org.dkcorp.vktesttask.dto.response.PostDto;
import org.dkcorp.vktesttask.service.PostsProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Operations related to posts")
public class PostsProxyController {
    private final PostsProxyService postsProxyService;

    @GetMapping
    @Operation(summary = "Fetch all posts", description = "Fetch all posts from the server")
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

    @PostMapping("/{postId}/comments")
    @Operation(summary = "Add new comment", description = "Add new comment for the post")
    public CommentDto addPostComment(@PathVariable Long postId, @RequestBody IncomingCommentDto incomingCommentDto) {
        return postsProxyService.addPostComment(postId, incomingCommentDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create post", description = "Create new post")
    public PostDto createPost(@RequestBody IncomingPostDto incomingPostDto) {
        return postsProxyService.createPost(incomingPostDto);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "Update post", description = "Update post by its id")
    public PostDto updatePost(@PathVariable Long postId, @RequestBody IncomingPostDto incomingPostDto) {
        return postsProxyService.updatePost(postId, incomingPostDto);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "Delete post", description = "Delete post by its id")
    public void deletePost(@PathVariable Long postId) {
        postsProxyService.deletePost(postId);
    }
}
