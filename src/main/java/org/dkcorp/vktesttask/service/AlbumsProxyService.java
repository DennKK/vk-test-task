package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.AlbumDto;
import org.dkcorp.vktesttask.dto.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.PhotoDto;

import java.util.List;

public interface AlbumsProxyService {
    List<AlbumDto> getAllAlbums();

    AlbumDto getAlbumById(Long id);

    List<PhotoDto> getAlbumPhotos(Long id);

    AlbumDto createAlbum(IncomingAlbumDto incomingAlbumDto);

    AlbumDto updateAlbum(Long id, IncomingAlbumDto incomingAlbumDto);

    void deleteAlbum(Long id);
}
