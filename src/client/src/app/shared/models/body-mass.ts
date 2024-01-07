export interface BodyFat {
  bodyFat: number;
  percentage: string;
}

export interface Height {
  height: number;
  heightType: string;
}

export interface Weight {
  weight: number;
  weightType: string;
}

export interface CreateBmiResource {
    weight: number,
    height: number
}

export interface BmrDetailsResource {
  calories: number,
  unitType: string
}

export interface BmiFormula {
  name: string,
  description: string
}