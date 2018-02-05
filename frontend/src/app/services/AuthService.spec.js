import angular from 'angular';
import 'angular-mocks';
import AuthService from './AuthService';

describe('AuthService service', () => {
  beforeEach(() => {
    angular
      .module('AuthService', [])
      .service('AuthService', AuthService);
    angular.mock.module('AuthService');
  });

  it('should', angular.mock.inject(AuthService => {
    expect(AuthService.getData()).toEqual(3);
  }));
});
