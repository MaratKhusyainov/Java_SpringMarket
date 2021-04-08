angular.module('app').controller('signInController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    $localStorage.afterSignInMethode();

    $scope.tryToSignUp = function (newuser) {
        $http.post(contextPath + '/api/v1/user', newuser)
            .then(function successCallback(response) {
                window.alert("Success");
                $localStorage.user = newuser;
                $localStorage.afterSignInMethode();
                delete newuser;
                $location.path('/');
            }, function errorCallback(response) {
                window.alert("This username or email already exist");
            });
    }
});