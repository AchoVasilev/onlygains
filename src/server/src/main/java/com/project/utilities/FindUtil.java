package com.project.utilities;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

public final class FindUtil {
    private FindUtil() {}

    public static <T> Optional<T> findByProperty(Collection<T> collection, Predicate<T> filter) {
        return collection.stream().filter(filter).findFirst();
    }
}
