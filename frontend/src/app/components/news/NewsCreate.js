class NewsCreateController {
  constructor() {
    this.text = 'My brand new component!';
  }
}

export const NewsCreate = {
  template: require('./NewsCreate.html'),
  controller: NewsCreateController
};

