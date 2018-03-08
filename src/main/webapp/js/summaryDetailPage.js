app.controller('summaryDetailPageCtrl', function ($scope, $log, $http, $interval, $routeParams) {
    $log.log("routeParams.summaryId", $routeParams.summaryId)


    $scope.initData = function () {
        $http({
            method: 'GET',
            url: 'summaries/' + $routeParams.summaryId
        }).then(function successCallback(response) {
            $log.log("summary", response);
            $scope.summary = response.data;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $log.log("summaries fail", response)
        });


        $http({
            method: 'GET',
            url: 'analyzation/' + $routeParams.summaryId
        }).then(function successCallback(response) {
            $log.log("analyzation", response);
            $scope.analyzation = response.data;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $log.log("analyzation fail", response)
        });
    }

    $scope.switchShowTerms = function () {
        $scope.showTerms = !$scope.showTerms;
    }

    $scope.initData();
});





