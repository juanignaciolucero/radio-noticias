class NewsCreateController {
  constructor() {
    this.news = {
      enabled: true
    };
    this.radios = [
      {
        id: 1,
        name: 'Radio 1'
      },
      {
        id: 2,
        name: 'Radio 2'
      },
      {
        id: 3,
        name: 'Radio 3'
      }
    ];
  }
}

export const NewsCreate = {
  template: require('./NewsCreate.html'),
  controller: NewsCreateController
};

