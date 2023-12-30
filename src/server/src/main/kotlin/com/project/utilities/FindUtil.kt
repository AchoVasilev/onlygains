package com.project.utilities

import java.util.Optional
import java.util.function.Predicate

object FindUtil {
    @JvmStatic
    fun <T> findByProperty(collection: Collection<T?>? = emptyList(), filter: (T?) -> Boolean): T? {
        return collection?.firstOrNull(filter)
    }
}
