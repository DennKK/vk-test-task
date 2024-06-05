package org.dkcorp.vktesttask.controller;

import org.dkcorp.vktesttask.dto.request.IncomingPostDto;
import org.dkcorp.vktesttask.dto.response.CommentDto;
import org.dkcorp.vktesttask.dto.response.PostDto;
import org.dkcorp.vktesttask.service.PostsProxyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostsProxyControllerTest {
    @Mock
    PostsProxyService service;

    @InjectMocks
    PostsProxyController controller;

    @Test
    @DisplayName("GET /api/posts returns list of posts")
    void handleGetAllPosts_ReturnsValidResponseEntity() {
        // given
        List<PostDto> expectedPosts = List.of(
                new PostDto(1L, 102L, "The greatest post of all time!", "Body of the post."),
                new PostDto(1L, 103L, "Good post.", "Body of the post.")
        );

        doReturn(expectedPosts).when(service).getAllPosts();

        // when
        List<PostDto> actualPosts = controller.getAllPosts();

        // then
        assertNotNull(actualPosts);
        assertEquals(expectedPosts, actualPosts);
        assertEquals(2, actualPosts.size());
        verify(service, times(1)).getAllPosts();
    }

    @Test
    @DisplayName("GET /api/posts/{postId} returns post by id")
    void handleGetPostById_ReturnsValidResponseEntity() {
        // given
        PostDto expectedPost = new PostDto(1L, 102L, "The greatest post of all time!", "Body of the post.");

        doReturn(expectedPost).when(service).getPostById(102L);

        // when
        PostDto actualPost = controller.getPostById(102L);

        // then
        assertNotNull(actualPost);
        assertEquals(expectedPost, actualPost);
        verify(service, times(1)).getPostById(102L);
    }

    @Test
    @DisplayName("GET /api/posts/{postId}/comments returns list of comments for post")
    void handleGetPostComments_ReturnsValidResponseEntity() {

        // given
        List<CommentDto> expectedComments = List.of(
                new CommentDto(102L, 1L, "Comment 1", "example@example.com", "Body of the comment1."),
                new CommentDto(102L, 2L, "Comment 2", "example1@example.com,", "Body of the comment2."));

        doReturn(expectedComments).when(service).getPostComments(102L);

        // when
        List<CommentDto> actualComments = controller.getPostComments(102L);

        // then
        assertNotNull(actualComments);
        assertEquals(expectedComments, actualComments);
        assertEquals(2, actualComments.size());
        verify(service, times(1)).getPostComments(102L);
    }

    @Test
    @DisplayName("POST /api/posts creates new post")
    void handleCreatePost_ReturnsValidResponseEntity() {
        // given
        IncomingPostDto incomingPost = new IncomingPostDto(1L, "The greatest post of all time!", "Body of the post.");
        PostDto expectedPost = new PostDto(1L, 102L, "The greatest post of all time!", "Body of the post.");

        doReturn(expectedPost).when(service).createPost(incomingPost);

        // when
        PostDto actualPost = controller.createPost(incomingPost);

        // then
        assertNotNull(actualPost);
        assertEquals(expectedPost, actualPost);
        ArgumentCaptor<IncomingPostDto> captor = ArgumentCaptor.forClass(IncomingPostDto.class);
        verify(service, times(1)).createPost(captor.capture());
        assertEquals(incomingPost, captor.getValue());
    }

    @Test
    @DisplayName("PUT /api/posts/{postId} updates post by id")
    void handleUpdatePost_ReturnsValidResponseEntity() {
        // given
        IncomingPostDto incomingPost = new IncomingPostDto(1L, "The greatest post of all time!", "Body of the post.");
        PostDto expectedPost = new PostDto(1L, 102L, "The greatest post of all time!", "Body of the post.");

        doReturn(expectedPost).when(service).updatePost(102L, incomingPost);

        // when
        PostDto actualPost = controller.updatePost(102L, incomingPost);

        // then
        assertNotNull(actualPost);
        assertEquals(expectedPost, actualPost);
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<IncomingPostDto> dtoCaptor = ArgumentCaptor.forClass(IncomingPostDto.class);
        verify(service, times(1)).updatePost(idCaptor.capture(), dtoCaptor.capture());
        assertEquals(102L, idCaptor.getValue());
        assertEquals(incomingPost, dtoCaptor.getValue());
    }

    @Test
    @DisplayName("DELETE /api/posts/{postId} deletes post by id")
    void handleDeletePost_ReturnsValidResponseEntity() {
        // when
        controller.deletePost(102L);

        // then
        verify(service, times(1)).deletePost(102L);
    }
}
