package org.sagittarius90.service.utils;

import java.util.List;

public interface BaseService<T> {

    List<T> getCollection(BaseFilter filter);

    T getSingleResultById(String id);

    boolean create(T fromModel);

    boolean update(String id, T fromModel);

}
