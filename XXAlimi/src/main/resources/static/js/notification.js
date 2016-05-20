function equalHeight(group) {    
    tallest = 0;    
    group.each(function() {       
        thisHeight = $(this).height();       
        if(thisHeight > tallest) {          
            tallest = thisHeight;       
        }    
    });    
    group.each(function() { $(this).height(tallest); });
};

function startWebSocket() {
	var socket = new SockJS('/WebSocket');
	
	stompClient = Stomp.over(socket);
	
	stompClient.connect({ }, function(frame) {
		hasConntected = true;
		
		stompClient.subscribe("/user/message/notification", function(data) {
			var message = data.body;
			console.log(message);
			notifyMessage(message);
		});
	});
}

function startWebSocketLow() {
	var wSocket = new WebSocket("ws://localhost:8080/notification");
	
	wSocket.onopen = function(e){  
		console.log("Socket has been opened! : "); 
	} 
	
	wSocket.onerror = function(event) {
		console.log("WebSocket Error : ", event.reason);
	}
	
	wSocket.onmessage = function(msg) {
		notifyMessage(msg);
	}
	
	wSocket.onclose = function(e){  
		console.log("Socket has been closed : ", e);
	}
}

function notifyMessage(msg) {
	if (!"Notification" in window) {
	    alert("This browser does not support desktop notification");
	}
	else if(Notification.permission === "granted") {
		var content = msg;
		var notification = new Notification("XXAlimi", {
			body: content,
			icon: '/img/Team Logo.png'
		});
	}
}

function notifyMe() {
		  // Let's check if the browser support notifications
		  if (!"Notification" in window) {
		    alert("This browser does not support desktop notification");
		  }
		  
		  // Let's check if the user is okay to get some notification
		  else if (Notification.permission === "granted") {
		    // If it's okay let's create a notification
			var content = document.getElementById("content").value;
		    var notification = new Notification("새글 알림 : ", { 
				body: content,
				icon: '/img/Team Logo.png' // optional
			});
		  }

		  // Otherwise, we need to ask the user for it's permission
		  // Note, Chrome does not implement the permission static property
		  // So we have to check for NOT 'denied' instead of 'default'
		  else if (Notification.permission !== 'denied') {
		    Notification.requestPermission(function (permission) {

		      // Whatever the user answers, we make sure Chrome store the information
		      if(!('permission' in Notification)) {
		        Notification.permission = permission;
		      }

		      // If the user is okay, let's create a notification
		      if (permission === "granted") {
		        var notification = new Notification("Hi there!");
		      }
		    });
		  }

		  // At last, if the user already denied any notification, and you 
		  // want to be respectful there is no need to bother him any more.
		}