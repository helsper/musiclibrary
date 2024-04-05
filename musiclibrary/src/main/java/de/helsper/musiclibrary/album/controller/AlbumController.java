package de.helsper.musiclibrary.album.controller;

import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.album.service.AlbumService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("albums")
public class AlbumController {

    final AlbumService albumService;

    @PostMapping
    public Album create(@Valid @RequestBody Album album) { return albumService.create(album); }

    @GetMapping
    public Iterable<Album> findAll() { return albumService.findAll(); }

    @GetMapping("{id}")
    public Album findById(@PathVariable Long id) { return albumService.findById(id); }

    @PutMapping("{id}")
    public Album update(@PathVariable Long id, @RequestBody Album album) { return albumService.update(id, album); }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) { albumService.deleteById(id); }
}
