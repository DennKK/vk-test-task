package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingPostDto;
import org.dkcorp.vktesttask.dto.response.CommentDto;
import org.dkcorp.vktesttask.dto.response.PostDto;
import org.dkcorp.vktesttask.exception.CustomClientException;
import org.dkcorp.vktesttask.exception.CustomServerException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve posts: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve posts: Server error")))
                .bodyToFlux(PostDto.class)
                .collectList()
                .block();
    }

    @Override
    public PostDto getPostById(Long id) {
        return webClient.get()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve post with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve post with id " + id + ": Server error")))
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public List<CommentDto> getPostComments(Long id) {
        return webClient.get()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id + "/comments")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve comments for post with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve comments for post with id " + id + ": Server error")))
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
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to create post: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to create post: Server error")))
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public PostDto updatePost(Long id, IncomingPostDto incomingPostDto) {
        return webClient.put()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(incomingPostDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to update post with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to update post with id " + id + ": Server error")))
                .bodyToMono(PostDto.class)
                .block();
    }

    @Override
    public void deletePost(Long id) {
        webClient.delete()
                .uri(POSTS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to delete post with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to delete post with id " + id + ": Server error")))
                .bodyToMono(Void.class)
                .block();
    }
}
