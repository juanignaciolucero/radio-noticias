export default restangularConfig;

/** @ngInject */
function restangularConfig(Restangular) {
  Restangular.setBaseUrl('http://local.radio.ba:8080/api');

  Restangular.setDefaultHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  });
}
