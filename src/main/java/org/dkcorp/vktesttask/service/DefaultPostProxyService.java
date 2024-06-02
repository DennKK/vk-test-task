package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.CommentDto;
import org.dkcorp.vktesttask.dto.IncomingPostDto;
import org.dkcorp.vktesttask.dto.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPostProxyService implements PostsProxyService {
    private final WebClient webClient;
    private final String POSTS_PREFIX = "/posts";
    private final String FORWARD_SLASH = "/";

    @Override
    public List<PostDto> getAllPosts() {
        return webClient.get()
                .uri(POSTS_PREFIX)
                .retrieve().bodyToFlux(PostDto.class)
                .collectList()
                .block();
    }

    @Override
    public PostDto getPostById(Long id) {
        return webClient.get()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public List<CommentDto> getPostComments(Long id) {
        return webClient.get()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id + "/comments").
                retrieve()
                .bodyToFlux(CommentDto.class)
                .collectList()
                .block();
    }

    @Override
    public PostDto createPost(IncomingPostDto incomingPostDto) {
        return webClient.post()
                .uri(POSTS_PREFIX)
                .bodyValue(incomingPostDto)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public PostDto updatePost(Long id, IncomingPostDto postDto) {
        return webClient.put()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(postDto)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public void deletePost(Long id) {
        webClient.delete()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
