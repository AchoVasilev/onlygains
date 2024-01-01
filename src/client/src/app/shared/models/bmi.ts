export interface BodyFat {
  bodyFat: number;
  percentage: string;
}

export interface Height {
  height: number;
  unitType: string;
}

export interface Weight {
  weight: number;
  unitType: string;
}

export interface CreateBmiResource {
    weight: number,
    height: number
}