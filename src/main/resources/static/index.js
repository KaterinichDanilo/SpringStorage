// angular.module('app', ['ngRoute', 'ngStorage']).controller('storeController', function ($scope, $rootScope, $http) {

// const contextPath = 'http://localhost:8189/store';
//
// $scope.loadClothes = function () {
//     $http.get(contextPath + '/clothes').then(function (response){
//         $scope.clothesList = response.data;
//         console.log(response.data());
//     });
// }
//
// $scope.getNewCart = function () {
//     $http.get(contextPath + '/cart').then(function (response){
//         $scope.cart = response.data;
//     });
// }
//
// $scope.increase = function (clothId) {
//     $http.get('http://localhost:8190/cart/api/v1/cart/inc/' + clothId).then(function (response){
//         $scope.cart = response.data;
//     });
// }
//
// $scope.reduce = function (clothId) {
//     $http.get(contextPath + '/cart/reduce/' + clothId, $scope.cart).then(function (response){
//         $scope.cart = response.data
//     });
// }
//
// $scope.removeFromCart = function (clothId) {
//     $http.get(contextPath + '/cart/remove/' + clothId, $scope.cart).then(function (response){
//         $scope.cart = response.data
//     });
// }
//
// $scope.isCartEmpty = function () {
//     return $scope.cart.totalPrice == 0;
// }
//
// $scope.loadClothes();
// $scope.getNewCart();
// });

angular.module('app', []);