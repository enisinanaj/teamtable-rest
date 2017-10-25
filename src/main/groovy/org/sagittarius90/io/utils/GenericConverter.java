package org.sagittarius90.io.utils;

import org.sagittarius90.model.utils.AbstractModel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GenericConverter<E, M extends AbstractModel> {

    E createFrom(M model);

    M createFrom(E entity);

    E updateEntity(E entity, M model);

    default List<M> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    default List<E> createFromModel(final Collection<M> models) {
        return models.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }
}
