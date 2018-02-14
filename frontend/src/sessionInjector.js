import {X_AUTH_TOKEN} from './app/constants/Metadata';
export default sessionInjector;

/** @ngInject */
function sessionInjector($cookies, $q, $state) {
  return {
    request: config => {
      config.headers[X_AUTH_TOKEN] = $cookies.get(X_AUTH_TOKEN);
      return config;
    },
    responseError: response => {
      if (response.status === 401 || response.status === 403) {
        $cookies.remove(X_AUTH_TOKEN);
        $state.go('auth.authLogin');
      }
      return $q.reject(response);
    }
  };
}
