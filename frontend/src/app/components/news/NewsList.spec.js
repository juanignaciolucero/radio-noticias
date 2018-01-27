import angular from 'angular';
import 'angular-mocks';
import {newsList} from './NewsList';

describe('newsList component', () => {
  beforeEach(() => {
    angular
      .module('newsList', ['app/components/news/NewsList.html'])
      .component('newsList', newsList);
    angular.mock.module('newsList');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<newsList></newsList>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
