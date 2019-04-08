/* eslint-disable camelcase,semi,angular/log */
class AdEditController {
  constructor(adService) {
    this.adService = adService;
  }
}
export const AdEdit = {
  template: require('./AdEdit.html'),
  controller: ['adService', AdEditController],
  bindings: {
    ad: '<'
  }
};
