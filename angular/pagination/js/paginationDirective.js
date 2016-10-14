'use strict';
(function () {
    angular.module('template/pageInit/pageInit.html', []).run(['$templateCache',function($templateCache) {
            $templateCache.put('template/pageInit/pageInit.html',
                '<ul class="pagination-main">\n'+
                '  <li class="prev-page" ng-class="{disabled:pageData.pageNo==1}" title="首页">\n'+
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==1 || on_loadPage(1)"><span class="fa fa-fast-backward"></span></a>\n'+
                '  </li>\n'+
                '  <li class="prev-page" ng-class="{disabled:pageData.pageNo==1}">\n'+
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==1 || on_prev()" title="上一页"><span class="fa fa-step-backward"></span></a>\n'+
                '  </li>\n'+
                '  <li class="data-page" ng-repeat="page in pageData.pages track by $index" ng-class="{\'first-page\': page==1, \'last-page\': page==pageData.totalPage,\'active\': page==pageData.pageNo}">\n'+
                '    <a ng-if="page!=\'...\'" href="javascript:void(0);" ng-class="{\'bg-custom\': page==pageData.pageNo}" ng-click="on_loadPage(page)">{{ page }}</a>\n'+
                '    <a ng-if="page==\'...\'" href="javascript:void(0);" ng-class="{\'bg-custom\': page==pageData.pageNo}" ng-click="on_loadPage(pageData.pageNo, $index)">{{ page }}</a>\n'+
                '  </li>\n'+
                '  <li class="next-page" ng-class="{disabled:pageData.pageNo==pageData.totalPage}">\n'+
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==pageData.totalPage || on_next()" title="下一页"><span class="fa fa-step-forward"></span></a>\n'+
                '  </li>\n'+
                '  <li class="next-page" ng-class="{disabled:pageData.pageNo==pageData.totalPage}">\n'+
                '    <a href="javascript:void(0);" ng-click="pageData.pageNo==pageData.totalPage || on_loadPage(pageData.totalPage)" title="尾页"><span class="fa fa-fast-forward"></span></a>\n'+
                '  </li>\n'+
                '  <li class="skip-page"><div><input type="text" placeholder="" ng-model="inpage">\n'+
                '    <input type="button" value="go" ng-click="on_loadPage(inpage)"></div>\n'+
                '  </li>\n'+
                '  <li class="data-num"><a class="cursor-text" href="#"><span>共{{pageData.totalRecord}}条</span></a></li>\n'+
                '</ul>\n'+
                ''
            );
        }
    ]);
    angular.module('pagination', ['template/pageInit/pageInit.html'])
        .directive('pagination',['pageinitTemplate', function(pageinitTemplate) {
            return {
                restrict   : 'AE',
                templateUrl: function (tElement, tAttrs) {
                    return tAttrs.templateUrl || pageinitTemplate.getPath();
                },
                replace    : true,
                scope      : {
                    pageData        : '=',
                    prev            : '&',
                    next            : '&',
                    loadPage        : '&',
                    maxSize         : '@'
                },
                link            : function(scope, element, attrs) {
                    var pages = [];
                    // 总页数小于等于7时 显示所有的页数
                    var getPagesLow = function(totalPage) {
                        var temp = [];
                        for (var i=1; i<totalPage+1; i++) {
                            temp.push(i);
                        }
                        return temp;
                    };
                    // 总页数大于7时 根据当前页获取7个页码数
                    var getPagesHigh = function(currentPage, totalPage) {
                        var temp = [];
                        if (currentPage <= 7) {
                            temp = [1, 2, 3, 4, 5, 6, 7, '...', totalPage];
                        } else if ((totalPage - currentPage) <= 2) {
                            temp = [
                                '...',
                                totalPage - 6, totalPage - 5, totalPage - 4,
                                totalPage - 3, totalPage - 2, totalPage - 1, totalPage
                            ];
                        }else{
                            var  start = 0;
                            for(var i=0;i<parseInt(totalPage/7);i++){
                                if(i*7<currentPage&&(i+1)*7>=currentPage){
                                    start = i*7 +1;
                                    break;
                                }
                            }
                            temp = [
                                '...',
                                start, start+1, start+2,
                                start+3, start+4, start+5,start+7, '...'
                            ];

                        }
                        return temp;
                    };
                    if (scope.pageData.totalPage <= 9) {
                        pages = getPagesLow(scope.pageData.totalPage);
                    } else {
                        pages = getPagesHigh(scope.pageData.pageNo, scope.pageData.totalPage);
                    }
                    scope.$watch('pageData',function (value) {
                        if (scope.pageData.totalPage <= 9) {
                            pages = getPagesLow(scope.pageData.totalPage);
                        } else {
                            pages = getPagesHigh(scope.pageData.pageNo, scope.pageData.totalPage);
                        }
                        scope.pageData.pages = pages;
                    });
                    scope.on_prev = function() {
                        if(scope.prev) {
                            scope.prev();
                        }
                    };
                    scope.on_next = function() {
                        if(scope.next) {
                            scope.next();
                        }
                    };
                    scope.on_loadPage = function(page,index) {
                        scope.inpage = undefined;
                        if(0==index){
                            page = scope.pageData.pages[1]-1;
                        }else if(7<=index){
                            if(scope.pageData.pages[scope.pageData.pages.length-1] == '...'){
                                page = scope.pageData.pages[scope.pageData.pages.length-2]+1;
                            }else{
                                page = scope.pageData.pages[scope.pageData.pages.length-3]+1;
                            }
                        }
                        if(scope.loadPage) {
                            scope.loadPage({no:page});
                        }
                    };

                }
            };
        }])
        .provider('pageinitTemplate', function () {
            var templatePath = 'template/pageInit/pageInit.html';
            this.setPath = function (path) {
                templatePath = path;
            };

            this.$get = function () {
                return {
                    getPath: function () {
                        return templatePath;
                    }
                };
            };
        });
}).call(window);