
import angular from 'angular';
import 'jquery';
import 'bootstrap';
import './assets/libs/material-dashboard-v1.2.0/assets/css/material-dashboard.css';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material.min';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material-dashboard';

import {TodoService} from './app/todos/todos';
import {NewsService} from './app/services/NewsService';

import {App} from './app/containers/App';
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

import 'angular-ui-router';
import 'restangular';
import 'angular-ui-bootstrap';
import 'material-spinner';
import 'angular-upload';

import routesConfig from './routes';
import restangularConfig from './restangular';

import './index.scss';

angular
  .module('app', ['ui.router', 'restangular', 'ui.bootstrap', 'lr.upload'])
  .config(routesConfig)
  .run(restangularConfig)
  .service('todoService', TodoService)
  .service('newsService', NewsService)
  .component('app', App)
  .component('headerComponent', Header)
  .component('footerComponent', Footer)
  .component('mainSection', MainSection)
  .component('todoTextInput', TodoTextInput)
  .component('todoItem', TodoItem)
  .component('newsList', NewsList)
  .component('newsCreate', NewsCreate)
  .component('newsEdit', NewsEdit)
  .component('newsForm', NewsForm)
  .component('uploadMultimedia', UploadMultimedia);
