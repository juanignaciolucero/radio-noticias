
import angular from 'angular';
import 'jquery';
import 'bootstrap';
import './assets/libs/material-dashboard-v1.2.0/assets/css/material-dashboard.css';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material.min';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material-dashboard';

import {TodoService} from './app/todos/todos';
import {NewsService} from './app/services/NewsService';
import {AuthService} from './app/services/AuthService';
import {App} from './app/containers/App';
import {Auth} from './app/containers/Auth';
import {Header} from './app/components/Header';
import {MainSection} from './app/components/MainSection';
import {TodoTextInput} from './app/components/TodoTextInput';
import {TodoItem} from './app/components/TodoItem';
import {Footer} from './app/components/Footer';
import {NewsList} from './app/components/news/NewsList';
import {NewsCreate} from './app/components/news/NewsCreate';
import {NewsEdit} from './app/components/news/NewsEdit';
import {NewsForm} from './app/components/news/NewsForm';
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

import routesConfig from './routes';
import restangularConfig from './restangular';
import X_AUTH_TOKEN from './app/constants/Metadata';

import './index.scss';

angular
  .module('app', ['ui.router', 'restangular', 'ui.bootstrap', 'lr.upload', 'ngCookies', 'ngRoute', 'permission', 'permission.ng'])
  .run(restangularConfig)
  .run(PermPermissionStore => {
    PermPermissionStore
      .definePermission('isAuthenticated', authService => {
        return authService.isAuthenticated();
      });
  })
  .config(routesConfig)
  .service('todoService', TodoService)
  .service('newsService', NewsService)
  .service('authService', AuthService)
  .component('app', App)
  .component('auth', Auth)
  .component('headerComponent', Header)
  .component('footerComponent', Footer)
  .component('mainSection', MainSection)
  .component('todoTextInput', TodoTextInput)
  .component('todoItem', TodoItem)
  .component('newsList', NewsList)
  .component('newsCreate', NewsCreate)
  .component('newsEdit', NewsEdit)
  .component('newsForm', NewsForm)
  .component('uploadMultimedia', UploadMultimedia)
  .component('authLogout', AuthLogout)
  .component('authLogin', AuthLogin)
  .factory('sessionInjector', ($cookies, $q, $state) => {
    return {
      request: config => {
        config.headers['X-AUTH-TOKEN'] = $cookies.get(X_AUTH_TOKEN);
        return config;
      },
      responseError: response => {
        if (response.status === 401 || response.status === 403) {
          $cookies.remove(X_AUTH_TOKEN);
          $state.go('auth.authLogin');
        }
        return $q.reject(response);
      }
    };
  })
  .config(['$httpProvider', $httpProvider => {
    $httpProvider.interceptors.push('sessionInjector');
  }]);
