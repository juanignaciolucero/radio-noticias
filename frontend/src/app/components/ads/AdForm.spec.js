import angular from 'angular';
import 'angular-mocks';
import {adForm} from './AdForm';

describe('newsForm component', () => {
  beforeEach(() => {
    angular
      .module('newsForm', ['app/components/ads/AdForm.html'])
      .component('adForm', adForm);
    angular.mock.module('adForm');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<adForm></adForm>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
