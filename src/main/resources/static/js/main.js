/*global require*/
'use strict';

require.config({
	paths: {
		angular : 'lib/angular/angular',
        underscore : 'lib/underscore/underscore',
        jquery : 'lib/jquery/jquery',
        bootstrap : 'lib/bootstrap/bootstrap',
        'openid-jquery' : 'lib/openid-jquery/openid-jquery',
        'openid-client' : 'lib/openid-client/openid-client'
	},
	shim: {
		angular: {
			exports: 'angular'
		},
        underscore: {
            exports : '_'
        },
        bootstrap : {
            deps :['jquery']
        },
        'openid-jquery' : {
            deps: ['jquery', 'openid-client']
        }
	}
});

require(['angular', 'underscore', 'app', 'openid-jquery', 'controllers/todo', 'directives/todoFocus'], function (angular) {
	angular.bootstrap(document, ['todomvc']);
    openid.init('openid_identifier');
});


