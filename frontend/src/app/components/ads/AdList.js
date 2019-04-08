/* eslint-disable camelcase */
class AdListController {
  constructor(Restangular) {
    this.Restangular = Restangular;
    this.list = [
      {
        id: '1234',
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
      }
    ];
    this.totalCount = 0;
    this.currentPage = 1;
    // this.get();
    this.loading = false;
  }

  pageChanged() {
    this.get();
  }
  get() {
    const ctrl = this;
    ctrl.loading = true;
    ctrl.Restangular.all('ad').customGET('', {
      offset: (ctrl.currentPage - 1) * 10
    }).then(rsp => {
      ctrl.list = rsp.list;
      ctrl.totalCount = rsp.totalCount;
    }).finally(() => {
      ctrl.loading = false;
    });
  }
}

export const AdList = {
  template: require('./AdList.html'),
  controller: ['Restangular', AdListController]
};

