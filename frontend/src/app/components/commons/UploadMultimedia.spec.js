import angular from 'angular';
import 'angular-mocks';
import {uploadMultimedia} from './UploadMultimedia';

describe('uploadMultimedia component', () => {
  beforeEach(() => {
    angular
      .module('uploadMultimedia', ['app/components/commons/UploadMultimedia.html'])
      .component('uploadMultimedia', uploadMultimedia);
    angular.mock.module('uploadMultimedia');
  });

  it('should...', angular.mock.inject(($rootScope, $compile) => {
    const element = $compile('<uploadMultimedia></uploadMultimedia>')($rootScope);
    $rootScope.$digest();
    expect(element).not.toBeNull();
  }));
});
