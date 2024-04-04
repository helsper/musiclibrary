package de.helsper.musiclibrary.album.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
    POP("Pop"),
    METAL("Metal"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    HIP_HOP("Hip Hop"),
    R_AND_B("R&B"),
    EDM("EDM"),
    COUNTRY("Country"),
    RAGGAE("Reggae"),
    LATIN("Latin"),
    CLASSICAL("Classical");

    private final String text;
}
