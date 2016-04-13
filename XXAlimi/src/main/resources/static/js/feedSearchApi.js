google.load("feeds", "1");

function findFeeds(query) {
	google.feeds.findFeeds(query, feedSearchDone);
}
function feedSearchDone(result) {
	var el = document.getElementById("feedControl");
	if(result.error || result.entries.length <=0) {
		el.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\"><center><strong><i class=\"glyphicon glyphicon-exclamation-sign\"></i> Sorry!</strong> No Results Found.</center></div>";
		return;
	}
	var feedControl = new google.feeds.FeedControl();
	var html="";
	for(var i=0; i<10; i++) {
		feedControl.addFeed(result.entries[i].url, result.entries[i].title);
		html +="<a href=\"#\" id=\"fortest\" data-dismiss=\"modal\" title=\"Click!\" class=\"list-group-item\" onclick=\"setFeedUrl('" + result.entries[i].url + "')\">" +
				"<img src=\"http://www.google.com/s2/favicons?domain=" + result.entries[i].link + "\" alt=\"Favicon Image\">" +
				"<h4 class=\"list-group-item-heading\">" + result.entries[i].title +"</h4>" +
				"<p class=\"list-group-item-text\">" + result.entries[i].contentSnippet + "</p>" + "</a>";
	}
	feedControl.setLinkTarget(google.feeds.LINK_TARGET_BLANK);
	feedControl.setNumEntries(0);
	el.innerHTML = html;
}
function onSubmit() {
	inputChange(document.getElementById("input"));
	return false;
}
function inputChange(input) {
	var query = input.value.toLowerCase();
	var el = document.getElementById("feedControl");
	el.innerHTML = "<div class=\"progress\"><div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%\"><span class=\"sr-only\">Loading...</span></div></div>";
	if(!query) {
		query = "";
		input.value = query;
	}
	findFeeds(query);
}
function setFeedUrl(value) {
	var url = document.getElementById("feedUrl");
	url.value = value;
	$("#saveBtn").tooltip('show'); 
	//$("#saveBtn").button('reset').addClass("btn-success");
}
