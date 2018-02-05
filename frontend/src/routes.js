export default routesConfig;

/** @ngInject */
function routesConfig($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true).hashPrefix('!');
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('auth', {
      url: '/auth',
      component: 'auth'
    })
    .state('auth.authLogin', {
      url: '/login',
      component: 'authLogin'
    })
    .state('auth.authLogout', {
      url: '/logout',
      component: 'authLogout'
    })
    .state('app', {
      url: '/',
      component: 'app'
    })
    .state('app.newsList', {
      url: 'news',
      component: 'newsList'
    })
    .state('app.newsCreate', {
      url: 'news/create',
      component: 'newsCreate'
    }).state('app.newsEdit', {
      url: 'news/edit/:id',
      component: 'newsEdit',
      resolve: {
        news: (Restangular, $stateParams) => {
          return Restangular.one('news', $stateParams.id).get();
        }
      }
    });
}
