package de.helsper.musiclibrary.album.entity.repository;

import de.helsper.musiclibrary.album.entity.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
