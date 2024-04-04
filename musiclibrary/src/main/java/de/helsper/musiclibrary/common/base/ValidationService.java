package de.helsper.musiclibrary.common.base;

import de.helsper.musiclibrary.common.exceptions.BadRequestException;

import java.util.Objects;

public interface ValidationService<T> {

    default void checkIdNotNull(Long id) {
        if (id == null) throw new BadRequestException("ID not found");
    }

    default void checkNotNull(T t) {
        if (t == null) throw new BadRequestException("Given object is null");
    }

    default void checkIdEquality(Long id, Long id2) {
        if (!Objects.equals(id, id2)) {
            throw new BadRequestException("IDs do not match");
        }
    }
}
