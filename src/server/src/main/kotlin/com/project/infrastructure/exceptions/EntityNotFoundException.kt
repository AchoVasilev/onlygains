package com.project.infrastructure.exceptions

import java.util.UUID
import kotlin.reflect.KClass

class EntityNotFoundException(className: KClass<*>, id: UUID?) : RuntimeException(String.format("%s does not exist. %s=%s",
        className.simpleName, className.simpleName, id))
