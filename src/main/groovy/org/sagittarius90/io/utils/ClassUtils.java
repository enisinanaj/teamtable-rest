package org.sagittarius90.io.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClassUtils<T> {

    public ClassUtils() { }

    public void setIfNotNull(final Supplier<T> getter, final Consumer<T> setter) {
        T t = getter.get();

        if (null != t) {
            setter.accept(t);
        }
    }
}
