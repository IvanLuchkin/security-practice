const USERNAME_KEY = 'email';
const PASSWORD_KEY = 'password';

interface AuthCredentials {
  username: string | null;
  password: string | null;
}

export class AuthStorage {
  public static isLoggedIn(): boolean {
    return !!(this.getUsername() && this.getPassword());
  }

  public static storeCredentials(username: string, password: string): void {
    localStorage.setItem(USERNAME_KEY, username);
    localStorage.setItem(PASSWORD_KEY, password);
  }

  public static getCredentials(): AuthCredentials {
    return {
      username: this.getUsername(),
      password: this.getPassword(),
    };
  }

  public static getUsername(): string | null {
    return localStorage.getItem(USERNAME_KEY);
  }

  public static getPassword(): string | null {
    return localStorage.getItem(PASSWORD_KEY);
  }

  public static clear(): void {
    localStorage.removeItem(USERNAME_KEY);
    localStorage.removeItem(PASSWORD_KEY);
  }
}
