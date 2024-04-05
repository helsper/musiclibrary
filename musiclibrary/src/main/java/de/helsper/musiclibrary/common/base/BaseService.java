package de.helsper.musiclibrary.common.base;

public interface BaseService<T> extends ValidationService<T> {

    T create(T t);

    Iterable<T> findAll();

    T findById(Long id);

    T update(Long id, T t);

    void deleteById(Long id);
}
