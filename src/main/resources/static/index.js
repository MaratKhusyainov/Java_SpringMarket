angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            $scope.PaginationArray=$scope.generatePagesIndexes(1,$scope.ProductsPage.totalPages)
        });
    };

    $scope.generatePagesIndexes = function (startPage,endPage){
        let arr = [];
        for (let i =startPage; i < endPage+1; i++){
            arr.push(i);
        }
        return arr;
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };


    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});