package org.dkcorp.vktesttask.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.dto.request.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.request.IncomingPhotoDto;
import org.dkcorp.vktesttask.dto.response.AlbumDto;
import org.dkcorp.vktesttask.dto.response.PhotoDto;
import org.dkcorp.vktesttask.service.AlbumsProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums")
@Tag(name = "Albums", description = "Operations related to albums")
public class AlbumsProxyController {
    private final AlbumsProxyService albumsProxyService;

    @GetMapping
    @Operation(summary = "Fetch all albums", description = "Fetch all albums from the server")
    public List<AlbumDto> getAllAlbums() {
        return albumsProxyService.getAllAlbums();
    }

    @GetMapping("/{albumId}")
    @Operation(summary = "Fetch album", description = "Fetch album by its id")
    public AlbumDto getAlbumById(@PathVariable Long albumId) {
        return albumsProxyService.getAlbumById(albumId);
    }

    @GetMapping("/{albumId}/photos")
    @Operation(summary = "Fetch album photos", description = "Fetch all photos for the album")
    public List<PhotoDto> getAlbumPhotos(@PathVariable Long albumId) {
        return albumsProxyService.getAlbumPhotos(albumId);
    }

    @PostMapping("/{albumId}/photos")
    @Operation(summary = "Add album photo", description = "Add new photo to the album")
    public PhotoDto addAlbumPhoto(@PathVariable Long albumId, @RequestBody IncomingPhotoDto incomingPhotoDto) {
        return albumsProxyService.addAlbumPhoto(albumId, incomingPhotoDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create album", description = "Create new album")
    public AlbumDto createAlbum(@RequestBody IncomingAlbumDto incomingAlbumDto) {
        return albumsProxyService.createAlbum(incomingAlbumDto);
    }

    @PutMapping("/{albumId}")
    @Operation(summary = "Update album", description = "Update album by its id")
    public AlbumDto updateAlbum(@PathVariable Long albumId, @RequestBody IncomingAlbumDto incomingAlbumDto) {
        return albumsProxyService.updateAlbum(albumId, incomingAlbumDto);
    }

    @DeleteMapping("/{albumId}")
    @Operation(summary = "Delete album", description = "Delete album by its id")
    public void deleteAlbum(@PathVariable Long albumId) {
        albumsProxyService.deleteAlbum(albumId);
    }
}
