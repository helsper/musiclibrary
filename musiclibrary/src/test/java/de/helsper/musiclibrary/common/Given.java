package de.helsper.musiclibrary.common;

import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.album.entity.model.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Given {

    public static final Long VALID_ID = 1L;
    public static final Long INVALID_ID = 29L;

    public static Album getAlbumWithValues() {
        LocalDate releaseDate = LocalDate.of(2000, 10, 24);
        LocalDate created = LocalDate.of(2024,4,4);
        LocalDate updated = LocalDate.of(2024,4,5);

        Album album = new Album();
        album.setId(VALID_ID);
        album.setName("Hybrid Theory");
        album.setArtist("Linkin Park");
        album.setReleaseDate(releaseDate);
        album.setNumberOfSongs(12);
        album.setGenre(Genre.METAL);
        album.setDescription("Hybrid Theory ist das Debütalbum der US-amerikanischen Nu-Metal-Band Linkin Park. Der Albumtitel stammt vom ursprünglichen Namen der Band. Hybrid Theory erschien am 24. Oktober 2000 über Warner Bros. Records in den Vereinigten Staaten, in Europa erfolgte die Veröffentlichung im Februar 2001.");
        album.setRatings(List.of(1,5,2));
        album.setImage(null);
        album.setCreated(created);
        album.setUpdated(updated);

        return album;
    }

    public static Iterable<Album> getSeveralAlbums() {
        List<Album> albums = new ArrayList<>();
        LocalDate releaseDate = LocalDate.of(1981, 9, 29);
        LocalDate created = LocalDate.of(2024,4,4);
        LocalDate updated = LocalDate.of(2024,4,5);

        Album album = new Album();
        album.setId(2L);
        album.setName("4");
        album.setArtist("Foreigner");
        album.setReleaseDate(releaseDate);
        album.setNumberOfSongs(10);
        album.setGenre(Genre.ROCK);
        album.setDescription("4 ist das vierte Studioalbum der US-amerikanischen/britischen Rock-Band Foreigner.");
        album.setRatings(List.of(4,5,3));
        album.setImage(null);
        album.setCreated(created);
        album.setUpdated(updated);

        albums.add(getAlbumWithValues());
        albums.add(album);

        return albums;
    }
}
