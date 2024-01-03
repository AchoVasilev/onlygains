import { BodyFat, Weight, Height, BmrDetailsResource } from './body-mass';

export interface UserViewResource {
  id: string;
  fullName: string;
  username: string;
}

export interface UserWorkoutProfileDetailsResource {
  id: string;
  userId?: string;
  bodyFat?: BodyFat;
  weight?: Weight;
  height?: Height;
  gender?: string;
  age?: number;
  bmi?: number;
  bmr?: BmrDetailsResource;
}

export interface UpdateWorkoutProfileResource {
  height?: number | null;
  weight?: number | null;
  gender?: string | null;
  age?: number | null;
  bodyFat?: number | null;
}
