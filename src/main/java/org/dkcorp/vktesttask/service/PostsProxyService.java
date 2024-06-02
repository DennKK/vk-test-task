package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.CommentDto;
import org.dkcorp.vktesttask.dto.IncomingPostDto;
import org.dkcorp.vktesttask.dto.PostDto;

import java.util.List;

public interface PostsProxyService {
    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    List<CommentDto> getPostComments(Long id);

    PostDto createPost(IncomingPostDto incomingPostDto);

    PostDto updatePost(Long postId, IncomingPostDto incomingPostDto);

    void deletePost(Long id);
}
