export class NewsService {
  /** @ngInject */
  constructor(Restangular) {
    this.Restangular = Restangular;
  }

  onSave(news) {
    const params = {
      title: news.title,
      description: news.description,
      radios: news.radios,
      newsCategory: news.newsCategory,
      image: news.image.mediaId,
      user: {
        id: 1
      },
      featured: news.featured,
      enabled: true
    };
    if (news.audio && news.audio.mediaId) {
      params.audio = news.audio.mediaId;
    }
    let rest;
    if (news.id) {
      params.id = news.id;
      rest = this.Restangular.one('news', news.id).customPUT(params);
    } else {
      rest = this.Restangular.all('news').customPOST(params);
    }
    return rest;
  }
}
