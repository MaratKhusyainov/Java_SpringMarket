angular.module('app', ['angularUtils.directives.dirPagination']).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';


    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.sort = function (keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    };

    $scope.deleteProductById = function (p) {
        $http({
            method: 'GET',
            url: contextPath + '/products/delete/' + p
        }).then(function () {
            $scope.fillTable();
        });
    };

    $scope.fillTable();
});