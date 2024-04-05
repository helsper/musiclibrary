package de.helsper.musiclibrary.album.service;

import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.album.entity.repository.AlbumRepository;
import de.helsper.musiclibrary.common.base.BaseService;
import de.helsper.musiclibrary.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumService implements BaseService<Album> {

    private final AlbumRepository albumRepository;

    public Album create(Album album) {
        album.setId(null);
        Album createdAlbum = albumRepository.save(album);
        log.info("Album with id {} has been created.", createdAlbum.getId());

        return createdAlbum;
    }

    public Iterable<Album> findAll() {
        return albumRepository.findAll();
    }

    public Album findById(Long id) {
        checkIdNotNull(id);
        checkIdNotNegative(id);

        return albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album not found with id :" + id));
    }

    public ServiceResponse deleteById(Long id) {
        checkIdNotNull(id);
        checkIdNotNegative(id);

        Album albumToDelete = findById(id);

        if (isAlbumTooPopularToDelete(albumToDelete)) {
            return new ServiceResponse(false, "Album is too popular, it can't be deleted");
        }

        try {
            albumRepository.deleteById(albumToDelete.getId());
            log.info("Album with id {} has been deleted.", albumToDelete.getId());

            return new ServiceResponse(true, "Album deleted successfully");
        } catch (Exception e) {
            return new ServiceResponse(false, "Failed to delete album: " + e.getMessage());
        }
    }

    public Album update(Long id, Album album) {
        checkIdNotNull(id);
        checkIdNotNegative(id);
        checkIdEquality(id, album.getId());

        Album updatedAlbum = albumRepository.save(album);
        log.info("Album with id {} has been updated.", updatedAlbum.getId());

        return updatedAlbum;
    }

    public boolean isAlbumTooPopularToDelete(Album album) {
        OptionalDouble averageRating = calculateAverageRating(album);
        return averageRating.isPresent() && averageRating.getAsDouble() >= 4
                && album.getRatings().size() > 10;
    }

    private OptionalDouble calculateAverageRating(Album album) {
        return album.getRatings().stream()
                .mapToInt(Integer::intValue)
                .average();
    }
}
