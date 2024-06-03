package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.response.AlbumDto;
import org.dkcorp.vktesttask.dto.request.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.response.PhotoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAlbumProxyService implements AlbumsProxyService {
    private final WebClient webClient;
    private final String ALBUMS_PREFIX = "/albums";
    private final String FORWARD_SLASH = "/";

    @Override
    public List<AlbumDto> getAllAlbums() {
        return webClient.get()
                .uri(ALBUMS_PREFIX)
                .retrieve().bodyToFlux(AlbumDto.class)
                .collectList()
                .block();
    }

    @Override
    public AlbumDto getAlbumById(Long id) {
        return webClient.get()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    public List<PhotoDto> getAlbumPhotos(Long id) {
        return webClient.get()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id + "/photos").
                retrieve()
                .bodyToFlux(PhotoDto.class)
                .collectList()
                .block();
    }

    @Override
    public AlbumDto createAlbum(IncomingAlbumDto incomingAlbumDto) {
        return webClient.post()
                .uri(ALBUMS_PREFIX)
                .bodyValue(incomingAlbumDto)
                .retrieve()
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    public AlbumDto updateAlbum(Long id, IncomingAlbumDto incomingAlbumDto) {
        return webClient.put()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .bodyValue(incomingAlbumDto)
                .retrieve()
                .bodyToMono(AlbumDto.class)
                .block();
    }

    @Override
    public void deleteAlbum(Long id) {
        webClient.delete()
                .uri(ALBUMS_PREFIX + FORWARD_SLASH + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
