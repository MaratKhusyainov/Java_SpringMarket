angular.module('app', []).controller('indexController', function ($scope, $http) {
        const contextPath = 'http://localhost:8189/market';
        $scope.authorized = false;
        $scope.username = null;
        $scope.p = null;

        $scope.showProductsPage = function (pageIndex = 1) {
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
                    $scope.showProductsPage();
                });
        };

        $scope.deleteProductById = function (productId) {
            $http.delete(contextPath + '/api/v1/products/' + productId)
                .then(function (response) {
                    $scope.showProductsPage();
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

        $scope.showMyOrders = function () {
            $http({
                url: contextPath + '/api/v1/orders',
                method: 'GET'
            }).then(function (response) {
                $scope.MyOrders = response.data;
            });
        };

        $scope.sumPrice = function (OrderItems) {
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
                document.getElementById('products').style.display = "block";
                document.getElementById('info_form').style.display = "none";
            });
        };

        $scope.tryToAuth = function () {
            $http.post(contextPath + '/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $scope.username = $scope.user.username;
                        $scope.user.username = null;
                        $scope.user.password = null;
                        $scope.authorized = true;
                        $scope.showMyOrders();
                        $scope.showCart();
                    }
                }, function errorCallback(response) {
                    window.alert("Error");
                });
        }

        $scope.tryToSignUp = function (newuser) {
            $http.post(contextPath + '/api/v1/user', newuser)
                .then(function successCallback(response) {
                    window.alert("Success");
                    delete newuser.email;
                    $scope.user= newuser;
                    $scope.tryToAuth();
                }, function errorCallback(response) {
                    window.alert("This username or email already exist");
                });
        }


        $scope.createOrder = function () {
            $http.post(contextPath + '/api/v1/orders/create', $scope.order)
                .then(function (response) {
                    $scope.order = null;
                    $scope.showMyOrders();
                    $scope.showCart();
                    document.getElementById('info_form').style.display = "none";
                    document.getElementById('products').style.display = "block";
                });

        }

        $scope.showInfo = function () {
            if ($scope.authorized == false) {
                window.alert("Please sign in");
            } else {
                document.getElementById('info_form').style.display = "block";
                document.getElementById('products').style.display = "none";

            }
        }
        $scope.back = function () {
            document.getElementById('info_form').style.display = "none";
            document.getElementById('products').style.display = "block";

        }
        $scope.showProductsPage();
    }
);