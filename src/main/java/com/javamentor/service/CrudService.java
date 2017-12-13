package com.javamentor.service;

import java.util.List;

public interface CrudService<T> {

    List<T> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);

}
