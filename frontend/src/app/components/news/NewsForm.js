class NewsFormController {
  /* eslint max-params: ["error", 6] */
  /* eslint-env es6 */
  constructor(Restangular, $stateParams, $state, newsService, $scope) {
    if ($stateParams.id) {
      Restangular.one('news', $stateParams.id).get().then(news => {
        this.news = news;
        this.news.radios.forEach(radio => {
          this.radiosSelected.push(radio.id);
        });
        $scope.form.radios.$validate();
      });
    } else {
      this.news = {
        image: {
          id: ''
        },
        audio: {
          id: ''
        },
        newsCategory: {}
      };
    }
    this.loading = false;
    this.$state = $state;
    this.radios = [];
    this.Restangular = Restangular;
    this.newsService = newsService;
    this.getRadios();
    this.getCategories();
    this.radiosSelected = [];
  }

  getRadios() {
    const ctrl = this;
    ctrl.Restangular.all('radios').getList().then(radios => {
      this.radios = radios;
    });
  }

  getCategories() {
    const ctrl = this;
    ctrl.Restangular.all('categories').getList().then(categories => {
      this.categories = categories;
      if (!this.news.newsCategory.id) {
        this.news.newsCategory = categories[0];
      }
    });
  }

  onSave() {
    const ctrl = this;
    ctrl.loading = true;
    ctrl.news.radios = [];
    this.radiosSelected.forEach(el => {
      this.news.radios.push({
        id: el
      });
    });
    this.newsService.onSave(ctrl.news).then(() => {
      ctrl.$state.go('app.newsList');
    }).finally(() => {
      ctrl.loading = false;
    });
  }
}

export const NewsForm = {
  template: require('./NewsForm.html'),
  controller: ['Restangular', '$stateParams', '$state', 'newsService', '$scope', NewsFormController]
};

