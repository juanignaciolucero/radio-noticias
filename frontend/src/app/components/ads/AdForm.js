/* eslint-disable camelcase,angular/log */
const MAX_LENGTH_METADATA = {
  single: 1,
  multiple: 5
};

class AdFormController {
  /* eslint-env es6 */
  constructor($state, adService, toastr) {
    this.$state = $state;
    this.toastr = toastr;
    this.adService = adService;
    this.MAX_LENGTH_METADATA = MAX_LENGTH_METADATA;
  }

  addMetadata() {
    this.ad.metadata.push({
      image: {},
      urlRedirect: ''
    });
  }

  onDeleteMetadata(index) {
    this.ad.metadata = this.ad.metadata.filter((e, i) => {
      return i !== index;
    });
  }

  onSave() {
    const ctrl = this;
    ctrl.loading = true;
    this.adService.onSave(ctrl.ad)
      .then(() => {
        ctrl.toastr.success('Acción realizada con exito!');
      })
      .catch(() => {
        ctrl.toastr.error('Algo salio mal, vuelva a intentar.');
      })
      .finally(() => {
        ctrl.$state.go('app.adList');
        ctrl.loading = false;
      });
  }
}

export const AdForm = {
  template: require('./AdForm.html'),
  controller: ['$state', 'adService', 'toastr', AdFormController],
  bindings: {
    ad: '<'
  }
};

