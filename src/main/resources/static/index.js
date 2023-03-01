angular.module('store', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    // if ($localStorage.springWebUser) {
    //     $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    // }

    const contextPath = 'http://localhost:8189/store';
    $scope.pageNumber = 1;

    $scope.loadClothes = function (pageIndex = 1) {
        $http({
            url: contextPath + '/clothes',
            method: 'GET',
            params: {
                p: pageIndex,
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ClothesPage = response.data;
        });
    }

    $scope.nextPage = function () {
        $scope.pageNumber++;
        $scope.loadClothes($scope.pageNumber);
    }

    $scope.prevPage = function () {
        if ($scope.pageNumber > 1){
            $scope.pageNumber--;
        }
        $scope.loadClothes($scope.pageNumber);
    }

    $scope.addToCart = function (clothId) {
        $http.get(contextPath + '/cart/add/' + clothId).then(function (response){
            $scope.cart = response.data;
            console.log($scope.cart);
        });
    }

    $scope.increase = function (clothId) {
    $http.get(contextPath + '/cart/inc/' + clothId).then(function (response){
        $scope.cart = response.data;
        });
    }

    $scope.reduce = function (clothId) {
        $http.get(contextPath + '/cart/reduce/' + clothId).then(function (response){
            $scope.cart = response.data
        });
    }

    $scope.removeFromCart = function (clothId) {
        $http.get(contextPath + '/cart/remove/' + clothId).then(function (response){
            $scope.cart = response.data
        });
    }

    $scope.isCartEmpty = function () {
        return $scope.cart.totalPrice == 0;
    }

    $scope.getNewCart = function () {
        $http.get(contextPath + '/cart/new').then(function (response){
            $scope.cart = response.data;
            console.log($scope.cart.totalPrice == 0);
            console.log($scope.cart.totalPrice);
        });
    }

    $scope.loadClothes();
    $scope.getNewCart();
});