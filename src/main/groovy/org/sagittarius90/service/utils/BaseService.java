package org.sagittarius90.service.utils;

import org.sagittarius90.service.BaseFilter;

import java.util.List;

public interface BaseService<T> {

    List<T> getCollection();

    List<T> getFilteredCollection(BaseFilter filter);

    T getSingleResultById(String id);

    boolean create(T fromModel);

    boolean update(String id, T fromModel);

}
