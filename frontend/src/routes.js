export default routesConfig;

/** @ngInject */
function routesConfig($stateProvider, $urlRouterProvider, $locationProvider) {
  $locationProvider.html5Mode(true).hashPrefix('!');
  $urlRouterProvider.otherwise('/news');

  $stateProvider
    .state('auth', {
      url: '/auth',
      component: 'auth'
    })
    .state('auth.authLogin', {
      url: '/login',
      component: 'authLogin',
      resolve: {
        isNotAuthenticated: /** @ngInject */ ($state, authService) => {
          let isNotAuthenticated = true;
          if (authService.isAuthenticated()) {
            $state.go('app.newsList');
            isNotAuthenticated = false;
          }
          return isNotAuthenticated;
        }
      }
    })
    .state('auth.authLogout', {
      url: '/logout',
      component: 'authLogout',
      resolve: {
        isAuthenticated: /** @ngInject */ ($state, authService) => {
          let isAuthenticated = true;
          if (!authService.isAuthenticated()) {
            $state.go('auth.authLogin');
            isAuthenticated = false;
          }
          return isAuthenticated;
        }
      }
    })
    .state('app', {
      url: '/',
      component: 'app',
      abstract: true,
      resolve: {
        isAuthenticated: /** @ngInject */ ($state, authService) => {
          let isAuthenticated = true;
          if (!authService.isAuthenticated()) {
            $state.go('auth.authLogin');
            isAuthenticated = false;
          }
          return isAuthenticated;
        }
      }
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
    })
    .state('app.adList', {
      url: 'ad',
      component: 'adList'
    })
    .state('app.adEdit', {
      url: 'ad/edit/:id',
      component: 'adEdit'
    });
}
