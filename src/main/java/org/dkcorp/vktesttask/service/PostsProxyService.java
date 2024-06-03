package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.response.CommentDto;
import org.dkcorp.vktesttask.dto.request.IncomingPostDto;
import org.dkcorp.vktesttask.dto.response.PostDto;

import java.util.List;

public interface PostsProxyService {
    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    List<CommentDto> getPostComments(Long id);

    PostDto createPost(IncomingPostDto incomingPostDto);

    PostDto updatePost(Long id, IncomingPostDto incomingPostDto);

    void deletePost(Long id);
}
