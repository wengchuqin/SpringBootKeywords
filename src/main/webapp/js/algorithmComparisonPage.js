app.controller('algorithmComparisonPageCtrl', function ($scope, $log, $http, $interval) {

    $scope.startAnalyze = function () {
        $http({
            method: 'GET',
            url: 'analyze/start'
        }).then(function successCallback(response) {
            $log.log("analyze/start", response);
        });
    }

    $scope.init = function () {
        $http({
            method: 'GET',
            url: 'analyze/result'
        }).then(function successCallback(response) {
            $log.log("analyze/result", response);

            var data = [];
            var algorithmNameArr = ["p1", "r1", "f1"];
            var series = [];
            response.data.forEach(function (v) {
                data.push([v.p, v.r, v.f1]);
                series.push(v.algorithmName);
            });
            $scope.initChars(algorithmNameArr, series, data);
        });
    }

    $scope.initChars = function (labels, series, data) {
        $scope.labels = labels;
        $scope.series = series;
        $scope.data = data;
    }

    $scope.init();

});


