angular.module('app').controller('orderConfirmationController', function ($scope, $http, $location,$localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.submitOrder = function () {
        console.log($scope.isUserLoggedIn());
        if ($scope.isUserLoggedIn()) {
            $http.post(contextPath + '/api/v1/orders', $scope.order)
                .then(function (response) {
                    $location.path('/order_result/' + response.data.id);
                });
        } else {
            window.alert("Please sign in")
        }
        ;
    }
    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };
    $scope.cartContentRequest();
});