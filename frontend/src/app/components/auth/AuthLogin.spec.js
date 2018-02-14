import angular from 'angular';
import 'angular-mocks';
import {authLogin} from './AuthLogin';

describe('authLogin component', () => {
  beforeEach(() => {
    angular
      .module('authLogin', ['app/components/auth/AuthLogin.html'])
      .component('authLogin', authLogin);
    angular.mock.module('authLogin');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<authLogin></authLogin>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
