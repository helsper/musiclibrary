package de.helsper.musiclibrary.album.controller;

import de.helsper.musiclibrary.album.entity.model.Album;
import de.helsper.musiclibrary.album.service.AlbumService;
import de.helsper.musiclibrary.album.service.ServiceResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("albums")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public Album create(@Valid @RequestBody Album album) { return albumService.create(album); }

    @GetMapping
    public Iterable<Album> findAll() { return albumService.findAll(); }

    @GetMapping("{id}")
    public Album findById(@PathVariable Long id) { return albumService.findById(id); }

    @PutMapping("{id}")
    public Album update(@PathVariable Long id, @NotNull @RequestBody Album album) { return albumService.update(id, album); }

    @DeleteMapping("{id}")
    public ServiceResponse delete(@PathVariable Long id) { return albumService.deleteById(id); }
}
