app.controller('systemPageCtrl', function ($scope, $log, $http, $interval) {
    $scope.initTable = function () {
        $http({
            method: 'GET',
            url: 'summaries',
            params: {
                number: $scope.number,
                size: $scope.size
            }
        }).then(function successCallback(response) {
            $log.log("summaries", response);
            $scope.data = response.data;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $log.log("summaries fail", response)
        });
    }

    $scope.startCrawler = function () {
        $http({
            method: 'GET',
            url: 'crawler/start'
        }).then(function successCallback(response) {
            $log.log("crawler/start", response);
        });
    }

    $scope.stopCrawler = function () {
        $http({
            method: 'GET',
            url: 'crawler/stop'
        }).then(function successCallback(response) {
            $log.log("crawler/stop", response);
        });
    }
});


