package de.helsper.musiclibrary.common.base;

public interface BaseService<T> extends ValidationService<T> {

    public T create(T t);

    public Iterable<T> findAll();

    public T findById(Long id);

    public T update(Long id, T t);

    public void deleteById(Long id);
}
