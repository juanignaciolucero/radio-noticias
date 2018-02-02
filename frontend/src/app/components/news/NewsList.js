class NewsListController {
  constructor(Restangular) {
    this.Restangular = Restangular;
    this.news = [];
    this.totalCount = 0;
    this.currentPage = 0;
    this.getNews();
  }

  pageChanged() {
    this.getNews();
  }
  getNews() {
    const ctrl = this;
    ctrl.Restangular.all('news').customGET('', {
      offset: (ctrl.currentPage - 1) * 10
    }).then(rsp => {
      ctrl.news = rsp.news;
      ctrl.totalCount = rsp.totalCount;
    });
  }
  deleteNews(id) {
    this.Restangular.one('news', id).remove(() => {
      this.news = this.news.filter(news => {
        return news.id !== id;
      });
    });
  }
}

export const NewsList = {
  template: require('./NewsList.html'),
  controller: NewsListController
};

