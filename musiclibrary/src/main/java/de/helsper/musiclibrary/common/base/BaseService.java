package de.helsper.musiclibrary.common.base;

import de.helsper.musiclibrary.album.service.ServiceResponse;

public interface BaseService<T> extends ValidationService {

    T create(T t);

    Iterable<T> findAll();

    T findById(Long id);

    T update(Long id, T t);

    ServiceResponse deleteById(Long id);
}
