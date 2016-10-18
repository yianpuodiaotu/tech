'use strict';
(function () {
    angular.module('pagination', [])
        .directive('pagination', function () {
            return {
                restrict: 'E',
                template: '<ul class="pagination-main">\n' +
                '  <li class="prev-page" ng-class="{disabled:pageData.pageNo==1}">\n' +
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==1 || on_prev()" title="上一页"><span class="fa fa-caret-left"></span></a>\n' +
                '  </li>\n' +
                '  <li class="data-page" ng-repeat="page in pageData.pages track by $index" ng-class="{\'first-page\': page==1, \'last-page\': page==pageData.totalPage,\'active\': page==pageData.pageNo}">\n' +
                '    <a ng-if="page!=\'…\'" href="javascript:void(0);" ng-class="{\'bg-custom\': page==pageData.pageNo}" ng-click="on_loadPage(page)">{{ page }}</a>\n' +
                '    <a ng-if="page==\'…\'" href="javascript:void(0);" class="noborder" ng-class="{\'bg-custom\': page==pageData.pageNo}">{{ page }}</a>\n' +
                '  </li>\n' +
                '  <li class="next-page" ng-class="{disabled:pageData.pageNo==pageData.totalPage}">\n' +
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==pageData.totalPage || on_next()" title="下一页"><span class="fa fa-caret-right"></span></a>\n' +
                '  </li>\n' +
                '  <li class="skip-page"><div><input type="text" placeholder="" ng-model="inpage">\n' +
                '    <input type="button" value="go" ng-click="on_loadPage(inpage)"></div>\n' +
                '  </li>\n' +
                '  <li class="data-num"><a class="cursor-text" href="#"><span>共{{pageData.totalRecord}}条</span></a></li>\n' +
                '</ul>\n' +
                '',
                replace: true,
                scope: {
                    pageData: '=',
                    prev: '&',
                    next: '&',
                    loadPage: '&',
                    maxSize: '@'
                },
                link: function (scope, element, attrs) {
                    var pages = [];
                    // 总页数大于7时 根据当前页获取7个页码数
                    var getPages = function (currentPage, totalPage) {
                        var temp = [];
                        if (totalPage <= 9) {
                            for (var i = 1; i <= totalPage; i++) {
                                temp.push(i);
                            }
                        } else if (totalPage > 9 && currentPage <= 5) {
                            temp = [1, 2, 3, 4, 5, 6, 7, '…', totalPage];
                        } else if (totalPage > 9 && currentPage >= (totalPage - 4)) {
                            temp = [1, '…', totalPage - 6, totalPage - 5, totalPage - 4, totalPage - 3, totalPage - 2, totalPage - 1, totalPage];
                        } else if (totalPage > 9 && currentPage < (totalPage - 4) && currentPage > 5) {
                            temp = [1, '…', currentPage - 2, currentPage - 1, currentPage, currentPage + 1, currentPage + 2, '…', totalPage];
                        }
                        return temp;
                    };
                    pages = getPages(scope.pageData.pageNo, scope.pageData.totalPage);
                    scope.$watch('pageData', function (value) {
                        pages = getPages(scope.pageData.pageNo, scope.pageData.totalPage);
                        scope.pageData.pages = pages;
                    });
                    scope.on_prev = function () {
                        if (scope.prev) {
                            scope.prev();
                        }
                    };
                    scope.on_next = function () {
                        if (scope.next) {
                            scope.next();
                        }
                    };
                    scope.on_loadPage = function (page, index) {
                        scope.inpage = undefined;
                        if (0 == index) {
                            page = scope.pageData.pages[1] - 1;
                        } else if (7 <= index) {
                            if (scope.pageData.pages[scope.pageData.pages.length - 1] == '...') {
                                page = scope.pageData.pages[scope.pageData.pages.length - 2] + 1;
                            } else {
                                page = scope.pageData.pages[scope.pageData.pages.length - 3] + 1;
                            }
                        }
                        if (scope.loadPage) {
                            scope.loadPage({no: page});
                        }
                    };

                }
            };
        });
}).call(window);