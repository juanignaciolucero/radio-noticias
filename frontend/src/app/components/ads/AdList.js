/* eslint-disable camelcase,semi,angular/log */
class AdListController {
  constructor(adService, $scope, $rootScope, $state) {
    this.adService = adService;
    this.loading = false;
    this.list = [];
    const onRadiochange = $rootScope.$watch('currentRadioId', currentRadioId => {
      if (currentRadioId) {
        $state.transitionTo('app.adList', {radio_id: currentRadioId}, {notify: false});
        this.get();
      }
    }, true);

    $scope.$on('$destroy', () => {
      onRadiochange();
    });
  }

  pageChanged() {
    this.get();
  }

  get() {
    const ctrl = this;
    ctrl.loading = true;
    ctrl.adService.list()
      .then(rsp => {
        ctrl.list = rsp.plain();
      })
      .finally(() => {
        ctrl.loading = false;
      });
  }
}

export const AdList = {
  template: require('./AdList.html'),
  controller: ['adService', '$scope', '$rootScope', '$state', AdListController]
};
