/* eslint-disable angular/log */
export class AdService {
  /** @ngInject */
  constructor(Restangular) {
    this.Restangular = Restangular;
  }

  onSave(ad) {
    const body = angular.copy(ad);
    body.metadata = body.metadata.filter(e => {
      return Object.keys(e.image).length > 0;
    });
    console.log(body.metadata);
    return this.Restangular.one('ad', ad.id).customPUT(body);
  }
}
