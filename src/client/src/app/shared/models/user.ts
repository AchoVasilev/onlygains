import { BodyFat, Weight, Height, BmrDetailsResource } from './body-mass';

export interface UserViewResource {
  id: string;
  fullName: string;
  username: string;
}

export interface UserWorkoutProfileDetailsResource {
  id: string;
  userId: string | null;
  bodyFat: BodyFat | null;
  weight: Weight | null;
  height: Height | null;
  gender: string | null;
  age: number | null;
  bmi: number | null;
  bmr: BmrDetailsResource | null;
}

export interface UpdateWorkoutProfileResource {
  height?: number | null;
  weight?: number | null;
  gender?: string | null;
  age?: number | null;
  bodyFat?: number | null;
}
