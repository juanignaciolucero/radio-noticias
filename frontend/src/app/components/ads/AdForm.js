/* eslint-disable camelcase,angular/log */
const MAX_LENGTH_METADATA = 5;
class AdFormController {
  /* eslint max-params: ["error", 6] */
  /* eslint-env es6 */
  constructor($state, adService) {
    this.ad = {
      tab_name: 'NYP',
      tab_section: 'banner_izquierda_square',
      type: 'multiple',
      enabled: true,
      radio_id: 'RADIO10',
      metadata: [
        {
          image: {
            mediaId: '81f74724-c1a0-43b4-9a15-b8054ddd3c0d',
            extension: 'jpg',
            name: 'download2653789462487579959.jpg',
            type: 'IMAGE',
            url: 'https://s3.amazonaws.com/radioneuquenasia/IMAGE/81f7/81f74724-c1a0-43b4-9a15-b8054ddd3c0d.jpg'
          },
          url_redirect: 'https://wwww.google.com'
        }
      ]
    };
    this.loading = false;
    this.$state = $state;
    this.adService = adService;
    this.adBlackMetadata();
  }

  adBlackMetadata() {
    if (this.ad.type === 'multiple' && this.ad.metadata.length < MAX_LENGTH_METADATA) {
      this.ad.metadata.push({
        image: {},
        url_redirect: '',
        mocked: true
      });
    }
  }

  onSaveMetadata() {
    // TODO set mocked flag to false
    this.adBlackMetadata();
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
  controller: ['$state', 'adService', AdFormController]
};

