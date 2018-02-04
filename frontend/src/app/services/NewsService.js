import MULTIMEDIA_URL from '../constants/Metadata';

export class NewsService {
  constructor(Restangular) {
    this.Restangular = Restangular;
  }

  getMultimediaUrl(mediaId, type) {
    return `${MULTIMEDIA_URL}/${type}/${mediaId.substring(0, 4)}/${mediaId}`;
  }

  onSave(news) {
    const params = {
      title: news.title,
      description: news.description,
      radios: news.radios,
      newsCategory: news.category,
      user: {
        id: 1
      },
      featured: news.featured,
      enabled: news.enabled
    };
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
