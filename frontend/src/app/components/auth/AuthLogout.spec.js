import angular from 'angular';
import 'angular-mocks';
import {authLogout} from './AuthLogout';

describe('authLogout component', () => {
  beforeEach(() => {
    angular
      .module('authLogout', ['app/components/auth/AuthLogout.html'])
      .component('authLogout', authLogout);
    angular.mock.module('authLogout');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<authLogout></authLogout>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
