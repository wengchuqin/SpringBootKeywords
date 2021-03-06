app.controller('algorithmComparisonPageCtrl', function ($scope, $log, $http, $interval) {

    $scope.startAnalyze = function () {
        $http({
            method: 'GET',
            url: 'analyze/start'
        }).then(function successCallback(response) {
            $log.log("analyze/start", response);
        });
    };

    $scope.initTable = function(){
        $http({
            method: 'GET',
            url: 'analyze/category'
        }).then(function successCallback(response) {
            $log.log("category", response);
            $scope.categoryList = response.data;
        }, function errorCallback(response) {
            // 请求失败执行代码
            $log.log("category fail", response)
        });
    }

    $scope.init = function () {
        $http({
            method: 'GET',
            url: 'analyze/result'
        }).then(function successCallback(response) {
            $log.log("analyze/result", response);

            var data = [];
            var algorithmNameArr = ["p", "r", "f1"];
            var series = [];
            response.data.forEach(function (v) {
                data.push([v.p, v.r, v.f1]);
                series.push(v.algorithmName);
            });
            $scope.initChars(algorithmNameArr, series, data);
        });
    };

    $scope.initChars = function (labels, series, data) {
        $scope.labels = labels;
        $scope.series = series;
        $scope.data = data;
    };

    $scope.queryStatus = function () {
        $http({
            method: 'GET',
            url: 'analyze/status'
        }).then(function successCallback(response) {
            $log.log("analyze/result", response);
            $scope.status = response.data;
        });
    };

    //自动刷新数据
    $interval(function () {
        console.log('刷新数据');
        $scope.queryStatus();
        $scope.init();
    }, 5 * 1000);


    $scope.init();
    $scope.initTable();

});


