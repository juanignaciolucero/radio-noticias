class AuthLoginController {
  constructor(Restangular, $cookies, $state, authService) {
    this.loading = false;
    this.error = false;
    this.$cookies = $cookies;
    this.$state = $state;
    this.Restangular = Restangular;
    this.authService = authService;
    this.auth = {
      username: '',
      password: ''
    };
  }
  onLogin() {
    this.loading = true;
    this.error = false;
    this.Restangular.all('login').post(this.auth).then(rsp => {
      this.authService.login(rsp.access_token);
      this.$state.go('app.newsList');
    }, () => {
      this.error = true;
    }).finally(() => {
      this.loading = false;
    });
  }
}

export const AuthLogin = {
  template: require('./AuthLogin.html'),
  controller: ['Restangular', '$cookies', '$state', 'authService', AuthLoginController]
};
