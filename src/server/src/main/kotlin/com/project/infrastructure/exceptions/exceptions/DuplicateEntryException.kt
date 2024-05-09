package com.project.infrastructure.exceptions.exceptions

import java.util.UUID

class DuplicateEntryException(className: Class<*>, id: UUID?) : RuntimeException(String.format("%s already exists. %s=%s", className.simpleName, className.simpleName, id))
