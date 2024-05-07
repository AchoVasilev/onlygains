export interface LoginResource {
  email?: string | null;
  password?: string | null;
}

export interface TokenResponseResource {
  token: string;
  expiresInSeconds: number;
  expiresAt: string;
}
