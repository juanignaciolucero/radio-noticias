import {X_AUTH_TOKEN} from '../constants/Metadata';

export class AuthService {
  constructor($cookies) {
    this.$cookies = $cookies;
  }
  isAuthenticated() {
    return (this.$cookies.get(X_AUTH_TOKEN));
  }
  logout() {
    this.$cookies.remove(X_AUTH_TOKEN);
  }
  login(accessToken) {
    this.$cookies.put(X_AUTH_TOKEN, accessToken);
  }
}
