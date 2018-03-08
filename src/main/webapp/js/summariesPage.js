app.filter('textLengthSet', function () {
    return function (value, wordwise, max, tail) {
        if (!value) return '';

        max = parseInt(max, 10);
        if (!max) return value;
        if (value.length <= max) return value;

        value = value.substr(0, max);
        if (wordwise) {
            var lastspace = value.lastIndexOf(' ');
            if (lastspace != -1) {
                value = value.substr(0, lastspace);
            }
        }

        return value + (tail || ' …');//'...'可以换成其它文字
    };
});
app.controller('summariesPageCtrl', function ($scope, $log, $http, $interval) {
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


    //初始值
    $scope.data = null;
    $scope.number = 0;
    $scope.size = 10;

    //自动刷新数据
    $interval(function () {
        console.log('刷新数据');
        $scope.initTable();
    }, 10 * 1000);

    //监听number的变化
    $scope.$watch('number', function (newValue, oldValue) {
        $log.log("newValue", newValue);
        $log.log("oldValue", oldValue);
        if (newValue < 0) {
            $scope.number = oldValue;
            return;
        }

        if ($scope.data && $scope.data.totalPages && newValue >= $scope.data.totalPages) {
            $scope.number = oldValue;
            return;
        }

        $scope.initTable();
    });
});


