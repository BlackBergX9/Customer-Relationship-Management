package org.blackbergx9.customerhandler;

public interface Repository<T, ID> {

    T save (T entity);

    T findById(ID id);

    Iterable<T> findAll();

    void deleteById(ID id);


}
