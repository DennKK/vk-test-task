package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.dto.request.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.request.IncomingPhotoDto;
import org.dkcorp.vktesttask.dto.response.AlbumDto;
import org.dkcorp.vktesttask.dto.response.PhotoDto;

import java.util.List;

public interface AlbumsProxyService {
    List<AlbumDto> getAllAlbums();

    AlbumDto getAlbumById(Long id);

    List<PhotoDto> getAlbumPhotos(Long id);

    PhotoDto addAlbumPhoto(Long id, IncomingPhotoDto incomingPhotoDto);

    AlbumDto createAlbum(IncomingAlbumDto incomingAlbumDto);

    AlbumDto updateAlbum(Long id, IncomingAlbumDto incomingAlbumDto);

    void deleteAlbum(Long id);
}
