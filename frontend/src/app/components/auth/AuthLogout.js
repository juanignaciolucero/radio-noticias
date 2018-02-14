class AuthLogoutController {
  constructor($state, authService) {
    authService.logout();
    $state.go('auth.authLogin');
  }
}

export const AuthLogout = {
  controller: ['$state', 'authService', AuthLogoutController]
};

