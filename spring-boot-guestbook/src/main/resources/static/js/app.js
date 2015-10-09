angular.module('guestbook', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/message', {
		templateUrl : 'message.html',
		controller : 'message'
	})
	.otherwise('/');

  })
  .controller('message', function($scope, $http) {
	  var _this=this;
	  this.loadData = function() {
		  $http.get("/message/all").success(function(data){
			  console.log("Get messages: " + JSON.stringify(data));
			  $scope.messageList = data;
		  });
	  }
	  $scope.save=function(){
		  $scope.waiting = true;
		  $http.get("/fortune").success(function(data){
			  console.log("Get fortune: " + JSON.stringify(data));
			  var fortune = data;
			  var message = {
					  name: $scope.message.name,
					  message: $scope.message.message,
					  fortune: fortune
			  	};
			  $http.post("/message", message).success(function(data){
				  console.log("Saved: " + data);
				  _this.loadData();
				  $scope.message.name = "";
				  $scope.message.message = "";
				  $scope.waiting = false;
			  });
		  });
	  }
	  this.loadData();
  })
  .controller('home', function($scope, $http, $location) {
	  $scope.goKillApp = function() {
		  $http.get("/killApp").success(function(data){
			  console.log("Killed: " + data);
		  });
	  }
	  $scope.goMessage = function() {
		  $location.path("/message");
	  }
	  this.initCtrl = function() {
		  $http.get("/cloudinfo").success(function(data){
			  $scope.cloudinfo = data;
		  });  
	  }
	  this.initCtrl();
  });