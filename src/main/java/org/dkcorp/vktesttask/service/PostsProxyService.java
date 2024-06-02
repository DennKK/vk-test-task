package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.CommentDto;
import org.dkcorp.vktesttask.dto.PostDto;

import java.util.List;

public interface PostsProxyService {
    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    List<CommentDto> getPostComments(Long id);
}
