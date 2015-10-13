angular.module('app', [])

    .factory('MyService', function($rootScope) {
      var mService = {};
      mService.connection = {};
      mService.connect = function() {
        // use this when deploying your application
        //var wsUri = "ws://"+window.location.hostname+"/ws" ;
        // use this when testing local
        $rootScope.wsUri = "ws://localhost:9000/ws";
        var ws = new WebSocket ( $rootScope.wsUri ) ;

        ws.onopen = function ( ) {
          console.log("connection established ...");
          mService.connection = ws;
        };
        ws.onmessage = function ( event ) {
          $rootScope.$apply(function() {
            console.log("Received data: " + JSON.parse(event.data));
            mService.items = JSON.parse(event.data);
          });
        }
      };
      mService.postMsg = function(data) {
        mService.connection.send(data);
      };
      mService.items = [];
      return mService;
    })

    .controller('ItemCtrl', function($scope, MyService, $rootScope) {

      $scope.items = MyService.items;

        $rootScope.$watchCollection(
            function() {
                return MyService.items;
            },
            function(newValue) {
                $scope.items = newValue;
            }
        );

      $scope.doSend = function(item) {
        var data = {
          action: "answer",
          itemId: item.id,
          price: item.bid
        };
        console.log("sending data" + JSON.stringify(data));
        MyService.postMsg(JSON.stringify(data));
      };

      MyService.connect();
    });


