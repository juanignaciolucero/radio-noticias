import angular from 'angular';
import 'angular-mocks';
import {adEdit} from './AdEdit';

describe('newsEdit component', () => {
  beforeEach(() => {
    angular
      .module('adEdit', ['app/components/ads/AdEdit.html'])
      .component('adEdit', adEdit);
    angular.mock.module('adEdit');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<adEdit></adEdit>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
