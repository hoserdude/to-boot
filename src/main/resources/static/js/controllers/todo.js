/*global define*/
'use strict';

/**
 * The main controller for the app. The controller:
 * - retrieves and persist the model via the todoStorage service
 * - exposes the model to the template and provides event handlers
 */
//Inject the storage type you want (Local or Remote)
define(['app', '../services/remoteTodoStorage', 'underscore'], function (app) {
	return app.controller('TodoController', ['$scope', '$location', 'todoStorage', 'filterFilter',
		function TodoController($scope, $location, todoStorage, filterFilter) {

			$scope.newTodoTitle = '';
			$scope.editedTodo = null;

            $scope.doWatch = function(data) {
                $scope.todos = data;
                $scope.$watch('todos', function () {
                    $scope.remainingCount = filterFilter($scope.todos, { completed: false }).length;
                    $scope.doneCount = $scope.todos.length - $scope.remainingCount;
                    $scope.allChecked = !$scope.remainingCount;
                }, true);
            };

            $scope.todos = todoStorage.get($scope.doWatch);

			if ($location.path() === '') {
				$location.path('/');
			}

			$scope.location = $location;

			$scope.$watch('location.path()', function (path) {
				$scope.statusFilter = (path === '/active') ?
					{ completed: false } : (path === '/completed') ?
					{ completed: true } : null;
			});

			$scope.addTodo = function () {
				var title = $scope.newTodoTitle.trim();
				if (!title.length) {
					return;
				}
                var newTodo = {
                    title: title,
                    completed: false
                };
                //Gains an ID and userID
                todoStorage.put(newTodo, function(savedTodo){
                    $scope.todos.push(savedTodo);
                    $scope.newTodoTitle = '';
                });
			};

			$scope.editTodo = function (todo) {
				$scope.editedTodo = todo;
			};

            $scope.todoCompleted = function(todo) {
                todo.completed = true;
                todoStorage.put(todo, function(savedTodo) {
                    var updatedEntryIndex = $scope.todos.indexOf(_.findWhere($scope.todos, { id: savedTodo.id }));
                    $scope.todos[updatedEntryIndex] = savedTodo;
                });
            };

			$scope.doneEditing = function (todo) {
                $scope.editedTodo = null;
                todo.title = todo.title.trim();

                if (!todo.title) {
                    todoStorage.delete(todo, function() {
                        $scope.removeTodo(todo);
                    });
                } else {
                    todoStorage.put(todo, function(savedTodo) {
                        var updatedEntryIndex = $scope.todos.indexOf(_.findWhere($scope.todos, { id: savedTodo.id }));
                        $scope.todos[updatedEntryIndex] = savedTodo;
                    });
                }
			};

            $scope.deleteTodo = function (todo) {
                todoStorage.delete(todo, function() {
                    $scope.removeTodo(todo);
                });
            }

			$scope.removeTodo = function (todo) {
				$scope.todos.splice($scope.todos.indexOf(todo), 1);
			};

			$scope.clearDoneTodos = function () {
				$scope.todos = $scope.todos.filter(function (val) {
					return !val.completed;
				});
			};

			$scope.markAll = function (done) {
                $scope.todos.forEach(function (todo) {
					todo.completed = done;
				});
			};
		}
	]);
});
