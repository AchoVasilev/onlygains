package com.project.application.models.exercise

import io.micronaut.serde.annotation.Serdeable
import jakarta.annotation.Nonnull
import java.util.Optional
import java.util.UUID

@Serdeable
data class CreateExerciseResource(@Nonnull val name: String,
                                  @Nonnull val translatedName: String,
                                  @Nonnull val description: String,
                                  @Nonnull val gifUrl: String,
                                  @Nonnull val imageUrl: String,
                                  val mainMuscleGroupsIds: Optional<List<String>>,
                                  val synergisticMuscleGroupsIds: Optional<List<String>>,
                                  val variations: Optional<List<UUID>>,
                                  val equipment: Optional<List<UUID>>)
