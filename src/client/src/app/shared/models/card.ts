export interface CardResource {
  id?: string;
  avatar?: string;
  imageUrl?: string;
  title?: string;
  subtitle?: string;
  text?: string;
}

export interface SideCardResource {
  id: string;
  title: string;
  createdAt: string;
  additionalUrl: string;
  additionalUrlName: string;
  imageUrl: string;
}
