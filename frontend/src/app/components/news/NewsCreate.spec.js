import angular from 'angular';
import 'angular-mocks';
import {newsCreate} from './NewsCreate';

describe('newsCreate component', () => {
  beforeEach(() => {
    angular
      .module('newsCreate', ['app/components/news/NewsCreate.html'])
      .component('newsCreate', newsCreate);
    angular.mock.module('newsCreate');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<newsCreate></newsCreate>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
