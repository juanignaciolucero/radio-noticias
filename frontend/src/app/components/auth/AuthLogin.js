/* eslint-disable max-params */
class AuthLoginController {
  constructor(Restangular, $cookies, $state, authService, $rootScope) {
    this.loading = false;
    this.error = false;
    this.$cookies = $cookies;
    this.$state = $state;
    this.Restangular = Restangular;
    this.authService = authService;
    this.$rootScope = $rootScope;
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
      this.$rootScope.getRadios();
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
  controller: ['Restangular', '$cookies', '$state', 'authService', '$rootScope', AuthLoginController]
};

