
import angular from 'angular';
import 'jquery';
import 'bootstrap';
import './assets/libs/material-dashboard-v1.2.0/assets/css/material-dashboard.css';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material.min';
import './assets/libs/material-dashboard-v1.2.0/assets/js/material-dashboard';

import {TodoService} from './app/todos/todos';
import {App} from './app/containers/App';
import {Header} from './app/components/Header';
import {MainSection} from './app/components/MainSection';
import {TodoTextInput} from './app/components/TodoTextInput';
import {TodoItem} from './app/components/TodoItem';
import {Footer} from './app/components/Footer';
import {NewsList} from './app/components/news/NewsList';
import 'angular-ui-router';

import routesConfig from './routes';

import './index.scss';

angular
  .module('app', ['ui.router'])
  .config(routesConfig)
  .service('todoService', TodoService)
  .component('app', App)
  .component('headerComponent', Header)
  .component('footerComponent', Footer)
  .component('mainSection', MainSection)
  .component('todoTextInput', TodoTextInput)
  .component('todoItem', TodoItem)
  .component('newsList', NewsList);
