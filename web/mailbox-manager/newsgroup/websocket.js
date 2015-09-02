/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var wsUri = 'ws://'+ document.location.host +'/Mailbox/websocket';
console.log(wsUri);
var websocket = new WebSocket(wsUri);
var textField = document.getElementById("textField");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");
var username;
websocket.onopen = function(evt) { onOpen(evt); };
websocket.onmessage = function(evt) { onMessage(evt); };
websocket.onerror = function(evt) { onError(evt); };
websocket.onclose = function(evt) { onClose(evt); };
var output = document.getElementById("output");

function join() {
    var list = document.getElementById("username-list");
    var index = list.selectedIndex;
    username = list[index].value;
    if(username) {
        websocket.send(username + " joined");
    }
    
}
function send_message() {
     if(username) {
        websocket.send(username + '-' + textField.value);
        
        var http = new XMLHttpRequest();
        var url = "http://"+document.location.host + document.location.pathname;
        var params = "username="+username+"&post-content="+textField.value;
        http.open("POST",url,true);
        http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        http.send(params);
        textField.value = "";
     } else {
         alert("Please join");
     }
 }
function onOpen() {
    writeToScreen("CONNECTED");
}
function onClose() {
    writeToScreen("DISCONNECTED");
    chatlog.innerHTML += username + " DISCONNECTED<br>";
}
function onMessage(evt) {
    writeToScreen("RECEIVED: " + evt.data);
    if (evt.data.indexOf("joined") !== -1) {
    users.innerHTML += evt.data.substring(0, evt.data.indexOf("joined")) + "\n";
    } else {
    chatlog.innerHTML += evt.data + "<br>";
    }
}
function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
function disconnect() {
    websocket.close();
}
function writeToScreen(message) {
    console.log(message);
}