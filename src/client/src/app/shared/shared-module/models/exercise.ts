import { EquipmentResource } from "./equipment";

export interface ExerciseListResource {
    id: string,
    name: string,
    imageUrl: string,
    equipment: EquipmentResource[]
}