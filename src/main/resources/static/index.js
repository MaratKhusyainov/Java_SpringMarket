angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    $scope.authorized = false;

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/products',
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
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/api/v1/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.deleteCartProductById = function (title) {
        let pos = $scope.Cart.items.map(function (e) {
            return e.productTitle;
        }).indexOf(title);
        $http.get(contextPath + '/api/v1/cart/product/' + pos)
            .then(function (response) {
                $scope.showCart();
            });
    }

    $scope.addToCart = function (id) {
        $http.get(contextPath + '/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.showCart();
            });
    }
    $scope.saveOrder = function (username) {
        if ($scope.authorized === false){
            alert("Please sign in")
        }else {
            $http.get(contextPath + '/api/v1/order/' + username)
                .then(function (response) {
                    $scope.OrderItems = response.data;
                    $scope.sumPrice($scope.OrderItems);
                });
        }
    }

    $scope.sumPrice= function (OrderItems){
        for (var i = 0, sum = 0; i < OrderItems.length; ++i) {
            sum += OrderItems[i].price;
        }
        $scope.sum = sum;
    }

    $scope.clearCart = function () {
        $http.get(contextPath + '/api/v1/cart/clear')
            .then(function (response) {
                $scope.showCart();
            });
    }



    $scope.updateQuantity = function (title, i) {
        let pos = $scope.Cart.items.map(function (e) {
            return e.productTitle;
        }).indexOf(title);
        $http.get(contextPath + '/api/v1/cart/change_quantity/' + pos + '/' + i)
            .then(function (response) {
                $scope.showCart();
            });
    };

    $scope.showCart = function () {
        $http.get(contextPath + '/api/v1/cart').then(function (response) {
            $scope.Cart = response.data;
        });
    };

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.user.password = null;
                    $scope.authorized = true;
                }
            }, function errorCallback(response) {
                window.alert("Error");
            });
    };

    $scope.showCart();
    $scope.fillTable();
});