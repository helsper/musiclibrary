package de.helsper.musiclibrary.common.base;

import de.helsper.musiclibrary.common.exceptions.BadRequestException;

import java.util.Objects;

public interface ValidationService {

    default void checkIdNotNull(Long id) {
        if (id == null) throw new BadRequestException("ID not found");
    }

    default void checkIdNotNegative(Long id) {
        if (id < 0) throw new BadRequestException("Invalid ID provided: " + id);
    }

    default void checkIdEquality(Long id, Long id2) {
        if (!Objects.equals(id, id2)) {
            throw new BadRequestException("IDs do not match");
        }
    }
}
