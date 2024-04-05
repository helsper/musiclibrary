package de.helsper.musiclibrary.album.service;

import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.common.Given;
import de.helsper.musiclibrary.album.entity.repository.AlbumRepository;
import de.helsper.musiclibrary.common.exceptions.BadRequestException;
import de.helsper.musiclibrary.common.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    AlbumRepository albumRepository;

    @InjectMocks
    AlbumService albumService;

    @AfterEach
    public void cleanUp() {
        reset(albumRepository, albumService);
    }

    @Test
    void create_WithValidArguments_ShouldReturnAlbum() {
        Album expected = Given.getAlbumWithValues();

        when(albumRepository.save(any(Album.class))).thenReturn(expected);
        Album actual = albumService.create(expected);

        assertEquals(expected, actual);
        verify(albumRepository, times(1)).save(expected);
    }

    @Test
    void create_WithInvalidArgument_ShouldThrowBadRequestException() {
        Album actual = null;

        assertThrows(BadRequestException.class, () -> albumService.create(actual));
    }

    @Test
    void findAll_ShouldReturnAlbumList() {
        Album expected = Given.getAlbumWithValues();

        when(albumRepository.findAll()).thenReturn(List.of(expected));
        List<Album> actual = (List<Album>) albumService.findAll();

        assertEquals(List.of(expected), actual);
    }

    @Test
    void findById_WithValidId_ShouldReturnOptional() {
        Album expected = Given.getAlbumWithValues();

        when(albumRepository.findById(Given.VALID_ID)).thenReturn(Optional.of(expected));
        Optional<Album> actual = Optional.ofNullable(albumService.findById(expected.getId()));

        assertTrue(actual.isPresent());
    }

    @Test
    void findById_WithInvalidID_ShouldThrowResourceNotFoundException() {
        assertThrows(ResourceNotFoundException.class, () -> albumService.findById(Given.INVALID_ID));
    }

    @Test
    void findById_WithNullId_ShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> albumService.findById(null));
    }

    @Test
    void findById_WithNegativeId_ShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> albumService.findById(-1L));
    }

    @Test
    void deleteById_WithValidID() {
        Album expected = Given.getAlbumWithValues();

        when(albumRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        albumService.deleteById(expected.getId());

        verify(albumRepository).deleteById(expected.getId());
    }

    @Test
    void deleteById_WithInvalidID_ShouldThrowResourceNotfoundException() {
        assertThrows(ResourceNotFoundException.class, () -> albumService.deleteById(Given.INVALID_ID));
    }

    @Test
    void isAlbumTooPopularToDelete_WithTooPopularAlbum_ShouldReturnTrue() {
        Album album = Given.getAlbumWithValues();
        album.setRatings(List.of(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5));

        assertTrue(albumService.isAlbumTooPopularToDelete(album));
    }

    @Test
    void isAlbumTooPopularToDelete_WithNotTooPopularAlbum_ShouldReturnFalse() {
        Album album = Given.getAlbumWithValues();
        album.setRatings(List.of(1, 1, 1, 1, 1));

        assertFalse(albumService.isAlbumTooPopularToDelete(album));
    }

    @Test
    void update_WithValidID_ShouldReturnReservation() {
        Long validId = Given.VALID_ID;
        Album expected = Given.getAlbumWithValues();

        when(albumRepository.save(any(Album.class))).thenReturn(expected);
        Album actual = albumService.update(validId, expected);

        assertEquals(expected, actual);
        verify(albumRepository, times(1)).save(expected);
    }

    @Test
    void update_WithInvalidID_ShouldThrowBadRequestException() {
        Long invalidId = Given.INVALID_ID;
        Album expected = Given.getAlbumWithValues();

        assertThrows(BadRequestException.class, () -> albumService.update(invalidId, expected));
    }

    @Test
    void update_WithInvalidReservation_ShouldThrowBadRequestException() {
        Long invalidId = Given.VALID_ID;
        Album expected = null;

        assertThrows(BadRequestException.class, () -> albumService.update(invalidId, expected));
    }

    @Test
    void update_WithDifferentIds_ShouldThrowBadRequestException() {
        Long invalidId = Given.VALID_ID;
        Album expected = Given.getAlbumWithValues();
        expected.setId(Given.INVALID_ID);

        assertThrows(BadRequestException.class, () -> albumService.update(invalidId, expected));
    }
}