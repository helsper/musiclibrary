package de.helsper.musiclibrary.album.entity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String artist;

    @NotNull
    LocalDate releaseDate;

    @NotNull
    Integer numberOfSongs;

    @NotNull
    @Enumerated(EnumType.STRING)
    Genre genre;

    @Lob
    @Column(name = "description", columnDefinition = "LONGTEXT")
    String description;

    @ElementCollection
    List<Integer> ratings;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    byte[] image;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate created;

    @UpdateTimestamp
    LocalDate updated;
}