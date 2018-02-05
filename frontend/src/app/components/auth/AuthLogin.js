class AuthLoginController {
  constructor(Restangular, $cookies, $state) {
    this.loading = false;
    this.$cookies = $cookies;
    this.$state = $state;
    this.Restangular = Restangular;
    this.auth = {
      username: 'admin',
      password: '1234'
    };
  }
  onLogin() {
    this.loading = true;
    this.Restangular.all('login').post(this.auth).then(rsp => {
      this.$cookies.put('access_token', rsp.access_token);
      this.$state.go('auth.authLogin');
    }).finally(() => {
      this.loading = false;
    });
  }
}

export const AuthLogin = {
  template: require('./AuthLogin.html'),
  controller: AuthLoginController
};

