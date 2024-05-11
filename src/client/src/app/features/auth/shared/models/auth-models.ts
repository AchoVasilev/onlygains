export interface LoginResource {
  email?: string | null;
  password?: string | null;
}

export interface AccessTokenResponseResource {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
  refreshToken: string;
  username: string;
  roles: string[];
  extensions: Map<string, string>;
}

export interface RefreshTokenResponseResource {
  accessToken: string;
  tokenType: string;
  expiresIn: number;
  refreshToken: string;
  extensions: Map<string, string>;
}

export interface UserModel {
  username: string;
  roles: string[];
}

export interface RegisterUserRequestResource {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
}
