package org.sagittarius90.service;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.List;

public interface BaseService<T> {
    List<T> getCollection();
    T getSingleResultById(String id);
    boolean create(T fromModel);
    boolean update(String id, T fromModel);
}
