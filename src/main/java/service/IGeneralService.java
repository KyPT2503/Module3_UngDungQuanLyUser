package service;

import java.util.List;

public interface IGeneralService<E> {
    List<E> findAll();

    boolean add(E e);

    boolean remove(int id);

    E findById(int id);

    boolean update(int id, E e);
}
