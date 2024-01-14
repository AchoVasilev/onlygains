export interface CategoryViewResource {
  id: string;
  imageUrl: string;
  name: string;
  translatedName: string;
}

export interface CategoryDTO {
  id?: string;
  translatedName?: string;
  name?: string;
}
