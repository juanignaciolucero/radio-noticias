class AuthLogoutController {
  constructor() {
    this.text = 'logout';
  }
}

export const AuthLogout = {
  template: require('./AuthLogout.html'),
  controller: AuthLogoutController
};

