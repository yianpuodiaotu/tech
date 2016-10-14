'use strict';
angular.module('paginationService',[])
    .factory('PageSync', ['$http', '$q', function Page($http, $q) {
        var service = {};
        service.load = function (url, currentPage, pageSize) {
            url = url + "&pageNum=" + currentPage + "&pageSize=" + pageSize
            return $http.get(url);
        }

        return service;
    }]);