/* eslint-disable angular/log */

import angular from 'angular';
import 'jquery';
import 'bootstrap';
import './assets/libs/material-dashboard-v1.2.0/assets/css/material-dashboard.css';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material.min';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material-dashboard';

import {NewsService} from './app/services/NewsService';
import {AuthService} from './app/services/AuthService';
import {AdService} from './app/services/AdService';
import {App} from './app/containers/App';
import {Auth} from './app/containers/Auth';
import {NewsList} from './app/components/news/NewsList';
import {NewsCreate} from './app/components/news/NewsCreate';
import {NewsEdit} from './app/components/news/NewsEdit';
import {NewsForm} from './app/components/news/NewsForm';
import {AdEdit} from './app/components/ads/AdEdit';
import {AdForm} from './app/components/ads/AdForm';
import {AdList} from './app/components/ads/AdList';
import {UploadMultimedia} from './app/components/commons/UploadMultimedia';
import {AuthLogout} from './app/components/auth/AuthLogout';
import {AuthLogin} from './app/components/auth/AuthLogin';

import 'angular-ui-router';
import 'restangular';
import 'angular-ui-bootstrap';
import 'material-spinner';
import 'angular-upload';
import 'angular-cookies';
import 'angular-permission';
import 'angular-route';
import 'angular-bootstrap-show-errors';
import 'angular-toastr';

import routesConfig from './routes';
import sessionInjector from './sessionInjector';
import {BACKEND_URL} from './app/constants/Metadata';

import './index.scss';

angular
  .module('app', [
    'ui.router',
    'restangular',
    'ui.bootstrap',
    'lr.upload',
    'ngCookies',
    'ngRoute',
    'permission',
    'permission.ng',
    'ui.bootstrap.showErrors',
    'toastr'
  ])
  .run(['Restangular', Restangular => {
    Restangular.setBaseUrl(BACKEND_URL + '/api');
    Restangular.setDefaultHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });
  }])
  .run(['PermPermissionStore', PermPermissionStore => {
    PermPermissionStore
      .definePermission('isAuthenticated', authService => {
        return authService.isAuthenticated();
      });
  }])
  .config(routesConfig)
  .run(['Restangular', '$rootScope', '$stateParams', (Restangular, $rootScope, $stateParams) => {
    Restangular.all('radios').getList()
      .then(radios => {
        $rootScope.radios = radios.plain();
        if ($stateParams.radio_id) {
          $rootScope.currentRadioId = parseInt($stateParams.radio_id, 10);
        } else {
          $rootScope.currentRadioId = $rootScope.radios[0].id;
        }
      });
  }])
  .service('newsService', NewsService)
  .service('authService', AuthService)
  .service('adService', AdService)
  .component('app', App)
  .component('auth', Auth)
  .component('newsList', NewsList)
  .component('newsCreate', NewsCreate)
  .component('newsEdit', NewsEdit)
  .component('newsForm', NewsForm)
  .component('adList', AdList)
  .component('adEdit', AdEdit)
  .component('adForm', AdForm)
  .component('uploadMultimedia', UploadMultimedia)
  .component('authLogout', AuthLogout)
  .component('authLogin', AuthLogin)
  .factory('sessionInjector', ['$cookies', '$q', '$state', sessionInjector])
  .config(['$httpProvider', $httpProvider => {
    $httpProvider.interceptors.push('sessionInjector');
  }]);
