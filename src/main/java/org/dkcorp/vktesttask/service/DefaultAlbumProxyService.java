package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.request.IncomingPhotoDto;
import org.dkcorp.vktesttask.dto.response.AlbumDto;
import org.dkcorp.vktesttask.dto.response.PhotoDto;
import org.dkcorp.vktesttask.exception.CustomClientException;
import org.dkcorp.vktesttask.exception.CustomServerException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAlbumProxyService implements AlbumsProxyService {
    private final WebClient webClient;
    private final String ALBUMS_PREFIX = "/albums";
    private final String FORWARD_SLASH = "/";

    @Override
    @Cacheable(value = "albumsCache", key = "'allAlbums'")
    public List<AlbumDto> getAllAlbums() {
        return webClient.get()
                .uri(ALBUMS_PREFIX)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve albums: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve albums: Server error")))
                .bodyToFlux(AlbumDto.class)
                .collectList()
                .block();
    }

    @Override
    @Cacheable(value = "albumsCache", key = "'albumid:' + #id")
    public AlbumDto getAlbumById(Long id) {
        return webClient.get()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve album with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve album with id " + id + ": Server error")))
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    @Cacheable(value = "albumsCache", key = "'albumPhotos:' + #id")
    public List<PhotoDto> getAlbumPhotos(Long id) {
        return webClient.get()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id + "/photos").
                retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to retrieve photos for album with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to retrieve photos for album with id " + id + ": Server error")))
                .bodyToFlux(PhotoDto.class)
                .collectList()
                .block();
    }

    @Override
    @CacheEvict(value = "albumsCache", allEntries = true)
    public PhotoDto addAlbumPhoto(Long id, IncomingPhotoDto incomingPhotoDto) {
        return webClient.post()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id + "/photos")
                .bodyValue(incomingPhotoDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to add new photo for album with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to add new photo for album with id " + id + ": Server error")))
                .bodyToMono(PhotoDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "albumsCache", allEntries = true)
    public AlbumDto createAlbum(IncomingAlbumDto incomingAlbumDto) {
        return webClient.post()
                .uri(ALBUMS_PREFIX)
                .bodyValue(incomingAlbumDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to create album: Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to create album: Server error")))
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "albumsCache", allEntries = true)
    public AlbumDto updateAlbum(Long id, IncomingAlbumDto incomingAlbumDto) {
        return webClient.put()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(incomingAlbumDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to update album with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to update album with id " + id + ": Server error")))
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    @CacheEvict(value = "albumsCache", allEntries = true)
    public void deleteAlbum(Long id) {
        webClient.delete()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, error ->
                        Mono.error(new CustomClientException("Failed to delete album with id " + id + ": Client error")))
                .onStatus(HttpStatusCode::is5xxServerError, error ->
                        Mono.error(new CustomServerException("Failed to delete album with id " + id + ": Server error")))
                .bodyToMono(Void.class)
                .block();
    }
}
