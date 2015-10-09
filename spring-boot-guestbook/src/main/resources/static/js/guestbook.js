/**
 * Created by sgupta on 9/22/15.
 */

var waitingImagePath = 'images/waiting.gif';
var headers = {"Content-Type": "application/json"};

function postService(urlName, args, callback, waitingDiv, outputDiv, isAsync, isAuth) {
    showWaiting(waitingDiv, "");
    var argsStr = JSON.stringify(args);
    $.ajax({url: urlName,
        data: argsStr,
        dataType: "json",
        success: function(data, textStatus) {
            clearDiv(waitingDiv);
            callback(data, textStatus, waitingDiv, outputDiv);
        },
        type: "POST",
        headers:headers,
        async: isAsync
    });
}

function getService(urlName, args, callback, waitingDiv, outputDiv, isAsync, isAuth) {
    showWaiting(waitingDiv, "");
    $.ajax({url: urlName,
        data: args,
        dataType: "json",
        success: function(data, textStatus) {
            clearDiv(waitingDiv);
            callback(data, textStatus, waitingDiv, outputDiv);
        },
        type: "GET",
        async: isAsync
    });
}

function showWaiting(divname, message) {
    $("#"+divname).text("").append("<img src='"+waitingImagePath+"'/>");
}

function clearDiv(divname) {
    $("#"+divname).text("");
}

function showMessage(msg) {
    $("#message").text(msg);
}

function doStartupSequence() {
    renderHomePageFunc();
}

var renderHomePageFunc = function() {
    clearDiv("content");
    getService("/message/all",
        {},
        function(data, textStatus, outputDiv){
            var myMessagesHtml = "<div id='nodes_MessagesListDiv'></div>";
            $("#content").append(myMessagesHtml);
            renderMessagesList(data, "nodes_MessagesListDiv");
        },
        "content", "content", true, false
    );
    renderMessagesList('nodes_MessagesListDiv');
}

function renderMessagesList(data, divname) {
    $("#"+divname).append("<table id='messagesList' width='80%'><tr style='border-bottom: solid;'><td>Name</td><td>Message</td></tr></table>");
    $.each(data, function(index, item){
        var itemHtml = "<tr><td>"+item["name"]+"</td><td>"+item["message"]+"</td></tr>";
        $("#messagesList").append(itemHtml);
    });
}

function saveMessage() {
    var name = $("#inputName").val();
    var message = $("#inputMessage").val();
    console.log("name: " + name + "; message: " + message);
    postService("/message",
        {"name":name, "message":message},
        renderHomePageFunc, "content", "content", true,  true);
}