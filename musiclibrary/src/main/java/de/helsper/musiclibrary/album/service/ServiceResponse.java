package de.helsper.musiclibrary.album.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse {
    private Boolean success;
    private String message;
}
