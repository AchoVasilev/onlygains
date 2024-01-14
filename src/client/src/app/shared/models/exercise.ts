import { EquipmentResource } from './equipment';
import { Selectable } from './selectable';

export interface ExerciseResource extends Selectable {}

export interface ExerciseListResource {
  id: string;
  name: string;
  imageUrl: string;
  equipment: EquipmentResource[];
}

export interface ExerciseDetailsResource {
  id: string;
  parentId?: string | null;
  name: string;
  gifUrl: string;
  translatedName: string;
  description: string;
  muscleGroups: MuscleGroupDetailsResource[];
  variations: ExerciseListResource[];
  equipment: EquipmentResource[];
  mainMuscleGroupsIds: string[];
  synergisticMuscleGroupsIds: string[];
}

export interface MuscleGroupDetailsResource {
  id: string;
  name: string;
  translatedName: string;
}

export interface CreateExerciseResource {
  name?: string | null;
  translatedName?: string | null;
  description?: string | null;
  gifUrl?: string | null;
  imageUrl?: string | null;
  mainMuscleGroupsIds?: string[] | null;
  synergisticMuscleGroupsIds?: string[] | null;
  variations?: string[] | null;
  equipment?: string[] | null;
}
