package com.project.domain.valueobjects

import java.util.UUID

@JvmInline
value class EntityId(val id: UUID = UUID.randomUUID())