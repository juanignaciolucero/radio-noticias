import angular from 'angular';
import 'angular-mocks';
import {newsEdit} from './NewsEdit';

describe('newsEdit component', () => {
  beforeEach(() => {
    angular
      .module('newsEdit', ['app/components/news/NewsEdit.html'])
      .component('newsEdit', newsEdit);
    angular.mock.module('newsEdit');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<newsEdit></newsEdit>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
