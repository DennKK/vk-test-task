package org.dkcorp.vktesttask.controller;

import org.dkcorp.vktesttask.dto.request.IncomingAlbumDto;
import org.dkcorp.vktesttask.dto.response.AlbumDto;
import org.dkcorp.vktesttask.dto.response.PhotoDto;
import org.dkcorp.vktesttask.service.AlbumsProxyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AlbumsProxyControllerTest {
    @Mock
    AlbumsProxyService service;

    @InjectMocks
    AlbumsProxyController controller;

    @Test
    @DisplayName("GET /api/albums returns list of albums")
    void handleGetAllAlbums_ReturnsValidResponseEntity() {
        // given
        List<AlbumDto> expectedAlbums = List.of(
                new AlbumDto(1L, 102L, "The greatest album of all time!"),
                new AlbumDto(1L, 103L, "Good album.")
        );

        doReturn(expectedAlbums).when(service).getAllAlbums();

        // when
        List<AlbumDto> actualAlbums = controller.getAllAlbums();

        // then
        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums, actualAlbums);
        assertEquals(2, actualAlbums.size());
        verify(service, times(1)).getAllAlbums();
    }

    @Test
    @DisplayName("GET /api/albums/{albumId} returns album by id")
    void handleGetAlbumById_ReturnsValidResponseEntity() {
        // given
        AlbumDto expectedAlbum = new AlbumDto(1L, 102L, "The greatest album of all time!");

        doReturn(expectedAlbum).when(service).getAlbumById(102L);

        // when
        AlbumDto actualAlbum = controller.getAlbumById(102L);

        // then
        assertNotNull(actualAlbum);
        assertEquals(expectedAlbum, actualAlbum);
        verify(service, times(1)).getAlbumById(102L);
    }

    @Test
    @DisplayName("GET /api/albums/{albumId}/photos returns list of photos for album")
    void handleGetAlbumPhotos_ReturnsValidResponseEntity() {
        // given
        List<PhotoDto> expectedPhotos = List.of(
                new PhotoDto(102L, 1L, "Photo 1", "https://example.com/photo1", "https://example.com/photo1/thumbnail"),
                new PhotoDto(102L, 2L, "Photo 2", "https://example.com/photo2", "https://example.com/photo2/thumbnail"),
                new PhotoDto(102L, 3L, "Photo 3", "https://example.com/photo3", "https://example.com/photo3/thumbnail")
        );

        doReturn(expectedPhotos).when(service).getAlbumPhotos(102L);

        // when
        List<PhotoDto> actualPhotos = controller.getAlbumPhotos(102L);

        // then
        assertNotNull(actualPhotos);
        assertEquals(expectedPhotos, actualPhotos);
        assertEquals(3, actualPhotos.size());
        verify(service, times(1)).getAlbumPhotos(102L);
    }

    @Test
    @DisplayName("POST /api/albums creates new album")
    void handleCreateAlbum_ReturnsValidResponseEntity() {
        // given
        IncomingAlbumDto incomingAlbum = new IncomingAlbumDto(1L, "The greatest album of all time!");
        AlbumDto expectedAlbum = new AlbumDto(1L, 102L, "The greatest album of all time!");

        doReturn(expectedAlbum).when(service).createAlbum(incomingAlbum);

        // when
        AlbumDto actualAlbum = controller.createAlbum(incomingAlbum);

        // then
        assertNotNull(actualAlbum);
        assertEquals(expectedAlbum, actualAlbum);
        ArgumentCaptor<IncomingAlbumDto> captor = ArgumentCaptor.forClass(IncomingAlbumDto.class);
        verify(service, times(1)).createAlbum(captor.capture());
        assertEquals(incomingAlbum, captor.getValue());
    }

    @Test
    @DisplayName("PUT /api/albums/{albumId} updates album by id")
    void handleUpdateAlbum_ReturnsValidResponseEntity() {
        // given
        IncomingAlbumDto incomingAlbum = new IncomingAlbumDto(1L, "The greatest album of all time!");
        AlbumDto expectedAlbum = new AlbumDto(1L, 102L, "The greatest album of all time!");

        doReturn(expectedAlbum).when(service).updateAlbum(102L, incomingAlbum);

        // when
        AlbumDto actualAlbum = controller.updateAlbum(102L, incomingAlbum);

        // then
        assertNotNull(actualAlbum);
        assertEquals(expectedAlbum, actualAlbum);
        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<IncomingAlbumDto> dtoCaptor = ArgumentCaptor.forClass(IncomingAlbumDto.class);
        verify(service, times(1)).updateAlbum(idCaptor.capture(), dtoCaptor.capture());
        assertEquals(102L, idCaptor.getValue());
        assertEquals(incomingAlbum, dtoCaptor.getValue());
    }

    @Test
    @DisplayName("DELETE /api/albums/{albumId} deletes album by id")
    void handleDeleteAlbum_ReturnsValidResponseEntity() {
        // when
        controller.deleteAlbum(102L);

        // then
        verify(service, times(1)).deleteAlbum(102L);
    }
}
