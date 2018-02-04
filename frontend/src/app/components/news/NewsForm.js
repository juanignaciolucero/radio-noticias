class NewsFormController {
  constructor(Restangular, $stateParams, $state, newsService) {
    if ($stateParams.id) {
      Restangular.one('news', $stateParams.id).get().then(news => {
        this.news = news;
      });
    } else {
      this.news = {
        image: '',
        audio: ''
      };
    }
    this.loading = false;
    this.$state = $state;
    this.radios = [];
    this.Restangular = Restangular;
    this.newsService = newsService;
    this.getRadios();
    this.getCategories();
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
    });
  }

  onSave() {
    const ctrl = this;
    ctrl.loading = true;
    this.newsService.onSave(ctrl.news).then(() => {
      ctrl.$state.go('app.newsList');
    }).finally(() => {
      ctrl.loading = false;
    });
  }
}

export const NewsForm = {
  template: require('./NewsForm.html'),
  controller: NewsFormController
};

