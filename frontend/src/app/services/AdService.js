/* eslint-disable angular/log,camelcase */
export class AdService {
  /** @ngInject */
  constructor(Restangular) {
    this.Restangular = Restangular;
  }

  get(id) {
    return this.Restangular.all('ads').get(id);
  }

  list() {
    return this.Restangular.all('ads').getList({radio_id: 1});
  }

  onSave(ad) {
    const body = angular.copy(ad);
    body.metadata = body.metadata.filter(e => {
      return Object.keys(e.image).length > 0;
    });
    return this.Restangular.one('ads', ad.id).customPUT(body);
  }
}
