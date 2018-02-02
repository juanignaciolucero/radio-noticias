class NewsService {
  list(Restangular) {
    return Restangular.all('news').get();
  }
}

export default NewsService;

