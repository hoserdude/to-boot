'use strict';

/**
 * Services that persists and retrieves TODOs from remoteStorage.
 */
define(['app'], function (app) {
    app.factory('todoStorage', ['$http', function ($http) {
        return {
            get: function (callback) {
                $http.get('api/v1/todo').success(function (data, status) {
                    if (data && status == 200) {
                        callback(data.todoDto);
                    } else {
                        return [];
                    }
                }).error(function (data, status) {
                    console.log(data)
                    return [];
                });
            },

            put: function (todo, callback) {

                if (todo.id) {
                    $http.put("api/v1/todo", todo).success(function (data, status) {
                        if (data && status == 200) {
                            console.log(data)
                            callback(data);
                        } else {
                            console.log(data);
                        }
                    }).error(function (data, status) {
                        console.log(data);
                    });
                } else {
                    $http.post("api/v1/todo", todo).success(function (data, status) {
                        if (data && status == 201) {
                            console.log(data)
                            callback(data);
                        } else {
                            console.log(data);
                        }
                    }).error(function (data, status) {
                        console.log(data);
                    });
                }
            },

            delete: function (todo, callback) {
                if (todo.id) {
                    $http.delete("api/v1/todo/"+todo.id).success(function (data, status) {
                        if (data && status == 200) {
                            console.log(data)
                            callback();
                        } else {
                            console.log(data);
                        }
                    }).error(function (data, status) {
                        console.log(data);
                    });
                }
            }
        }
    }]);
});


