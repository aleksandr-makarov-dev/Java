package org.example.dao;

import org.example.entities.EntityBase;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository <K extends Serializable,E extends EntityBase<K>>{
    E save(E entity);

    void delete(K id);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
