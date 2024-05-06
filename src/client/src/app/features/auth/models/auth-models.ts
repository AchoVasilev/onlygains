export interface LoginResource {
  email: string;
  password: string;
}

export interface TokenResponseResource {
  token: string;
  expiresInSeconds: number;
  expiresAt: string;
}
