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

    @Override
    public List<PostDto> getAllPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve().bodyToFlux(PostDto.class)
                .collectList()
                .block();
    }

    @Override
    public PostDto getPostById(Long id) {
        return webClient.get()
                .uri("/posts/" + id)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public List<CommentDto> getPostComments(Long id) {
        return webClient.get()
                .uri("/posts/" + id + "/comments").
                retrieve()
                .bodyToFlux(CommentDto.class)
                .collectList()
                .block();
    }

    @Override
    public PostDto createPost(IncomingPostDto incomingPostDto) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(incomingPostDto)
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }
}
