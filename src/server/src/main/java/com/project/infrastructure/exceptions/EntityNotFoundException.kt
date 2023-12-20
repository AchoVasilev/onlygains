package com.project.infrastructure.exceptions

import java.util.UUID

class EntityNotFoundException(className: Class<*>, id: UUID?) : RuntimeException(String.format("%s does not exist. %s=%s",
        className.simpleName, className.simpleName, id))
