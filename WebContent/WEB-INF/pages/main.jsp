<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Richard Golebiowski: websocket push</title>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	</head>
	<body>

		<div class="main_container">
			<div id="main_header">
				<ul>
					<li><a href="#" onclick="testwebsocketpush();">Click To Test Normal</a></li>
					<li><a href="#" onclick="testwebsocketpushAsync();">Click To Test Async</a></li>
				</ul>
			</div>
			<div class="main_content" id="main_content">
				<h1>Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;Main&nbsp;<br></h1>
			</div>
			<div class="main_footer">
				Richard Golebiowski
			</div>
		</div>
</body>

<script>
var webSocket = null;

function connect() {
	var wsURI = 'ws://' + window.location.host + '/PushSocket/mainwebsock';
	console.log("Connecting");
	webSocket = new WebSocket(wsURI);
	
	webSocket.onerror = function(event) {
		displayMessage(event.data);
	};
	
	webSocket.onopen = function(event) {
		displayMessage("Connection open.");
	};
	
	webSocket.onmessage = function(event) {
		displayMessage(event.data);
	};
	
}


function displayMessage(data) {
document.getElementById('main_content').innerHTML += '<br />' + data;
}

function disconnect() {
	               if (webSocket !== null) {
	            	   webSocket.close();
	            	   webSocket = null;
	                 }
	               displayMessage("WebSocket closed.");
}

$(window).on('beforeunload', function(){
	disconnect();
});

function testwebsocketpush(){
	if (webSocket == null) {connect();};
	$('div#main_content').empty();
	$("div#main_content").load('${pageContext.request.contextPath}/testwebsocketpush');
	//disconnect();
}

function testwebsocketpushAsync(){
	if (webSocket == null) {connect();};
	$('div#main_content').empty();
	$("div#main_content").load('${pageContext.request.contextPath}/testwebsocketpushasync');
	//disconnect();
}

</script>
</html>


