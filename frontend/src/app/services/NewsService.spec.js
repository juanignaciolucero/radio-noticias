import angular from 'angular';
import 'angular-mocks';
import NewsService from './NewsService';

describe('NewsService service', () => {
  beforeEach(() => {
    angular
      .module('NewsService', [])
      .service('NewsService', NewsService);
    angular.mock.module('NewsService');
  });

  it('should', angular.mock.inject(NewsService => {
    expect(NewsService.getData()).toEqual(3);
  }));
});
