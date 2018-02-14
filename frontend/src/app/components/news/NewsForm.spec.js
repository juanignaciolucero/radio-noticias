import angular from 'angular';
import 'angular-mocks';
import {newsForm} from './NewsForm';

describe('newsForm component', () => {
  beforeEach(() => {
    angular
      .module('newsForm', ['app/components/news/NewsForm.html'])
      .component('newsForm', newsForm);
    angular.mock.module('newsForm');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<newsForm></newsForm>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
