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
    }

    $scope.initData();
});





