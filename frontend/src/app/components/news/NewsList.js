class NewsListController {
  constructor(Restangular) {
    this.Restangular = Restangular;
    this.news = [];
    this.totalCount = 0;
    this.currentPage = 1;
    this.loading = true;
    this.getNews();
  }

  pageChanged() {
    this.getNews();
  }
  getNews() {
    const ctrl = this;
    ctrl.loading = true;
    ctrl.Restangular.all('news').customGET('', {
      offset: (ctrl.currentPage - 1) * 10
    }).then(rsp => {
      ctrl.news = rsp.news;
      ctrl.totalCount = rsp.totalCount;
    }).finally(() => {
      ctrl.loading = false;
    });
  }
  deleteNews(id) {
    this.Restangular.one('news', id).remove().then(() => {
      this.news = this.news.filter(news => {
        return news.id !== id;
      });
    });
  }
}

export const NewsList = {
  template: require('./NewsList.html'),
  controller: ['Restangular', NewsListController]
};

