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