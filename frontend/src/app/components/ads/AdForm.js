/* eslint-disable camelcase,angular/log */
const MAX_LENGTH_METADATA = 5;
class AdFormController {
  /* eslint-env es6 */
  constructor($state, adService) {
    this.$state = $state;
    this.adService = adService;
    this.$onInit = () => {
      this.adBlankMetadata();
    };
  }

  adBlankMetadata() {
    if (this.ad.type === 'multiple' && this.ad.metadata.length < MAX_LENGTH_METADATA) {
      this.ad.metadata.push({
        image: {},
        urlRedirect: ''
      });
    }
  }

  onSaveMetadata() {
    this.adBlankMetadata();
  }

  onDeleteMetadata(index) {
    this.ad.metadata = this.ad.metadata.filter((e, i) => {
      return i !== index;
    });
  }

  onSave() {
    const ctrl = this;
    ctrl.loading = true;
    this.adService.onSave(ctrl.ad).then(() => {
      ctrl.$state.go('app.adList');
    }).finally(() => {
      ctrl.loading = false;
    });
  }
}

export const AdForm = {
  template: require('./AdForm.html'),
  controller: ['$state', 'adService', AdFormController],
  bindings: {
    ad: '<'
  }
};

