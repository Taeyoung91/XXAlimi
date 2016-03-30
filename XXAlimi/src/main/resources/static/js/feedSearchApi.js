google.load("feeds", "1");

function findFeeds(query) {
	google.feeds.findFeeds(query, feedSearchDone);
}
function feedSearchDone(result) {
	var el = document.getElementById("feedControl");
	if(result.error || result.entries.length <=0) {
		el.innerHTML = '<center>No Results Found</center>';
		return;
	}
	var feedControl = new google.feeds.FeedControl();
	var html="";
	for(var i=0; i<10; i++) {
		feedControl.addFeed(result.entries[i].url, result.entries[i].title);
		html += "<div class=\"media\"><div class=\"media-left\"><img class=\"media-object\" alt=\"No Image\"></div>";
		html += "<div class=\"media-body\"><h4 class=\"media-heading\">";
		html += result.entries[i].title;
		html += "</h4><button class=\"btn btn-success btn-sm\" type=\"button\" value=\"";
		html += result.entries[i].url;
		html += "\" onclick=\"setFeedUrl(this.value)\" style=\"float: right;\" data-dismiss=\"modal\">Select!</button></div></div>";
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
}
