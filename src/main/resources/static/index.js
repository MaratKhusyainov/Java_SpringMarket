angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';


    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                p: pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex)
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
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

    $scope.deleteCartProductById = function (productId) {
        $http.delete(contextPath + '/cart/product/' + productId)
            .then(function (response) {
                $scope.fillCart();
            });
    }


    $scope.addToCartProductById = function (product, id) {
        let p = $scope.CartProductsList.findIndex(item => item.id === id);
        if (p === -1) {
            $http.post(contextPath + '/cart/product', product)
                .then(function (response) {
                    $scope.fillCart();
                });
        }
    }


    $scope.increaseProductInCart = function (id) {
        $scope.updateCountProductById(id, 1)
    }
    $scope.reduceProductInCart = function (id) {
        $scope.updateCountProductById(id, -1)
    }


    $scope.updateCountProductById = function (id, number) {
        let p = $scope.CartProductsList.findIndex(item => item.id === id);
        $http.put(contextPath + '/cart/' + p + "/" + number)
            .then(function (response) {
                $scope.fillCart();
            });
    };

    $scope.fillCart = function () {
        $http.get(contextPath + '/cart').then(function (response) {
            $scope.CartProductsList = response.data;
            document.getElementById("sum").value = $scope.CartProductsList.sum("cost");
        });
    };

    Array.prototype.sum = function (prop) {
        var total = 0
        for (var i = 0, _len = this.length; i < _len; i++) {
            total += this[i][prop]
        }
        return total
    }
    $scope.fillCart();
    $scope.fillTable();
});