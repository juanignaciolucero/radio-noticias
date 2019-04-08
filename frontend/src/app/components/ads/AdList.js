/* eslint-disable camelcase,semi,angular/log */
class AdListController {
  constructor(adService) {
    this.adService = adService;
    this.loading = true;
    this.list = [];
    this.get();
  }

  pageChanged() {
    this.get();
  }
  get() {
    const ctrl = this;
    ctrl.loading = true;
    ctrl.adService.list()
      .then(rsp => {
        ctrl.list = rsp;
      })
      .finally(() => {
        ctrl.loading = false;
      });
  }
}

export const AdList = {
  template: require('./AdList.html'),
  controller: ['adService', AdListController]
};

