var ws;
// Переопределить необходимые функции до инициализации веб-сокета!
var onWebSocketOpen = function (event) {};
var onWebSocketMessage = function (event) {};
var onWebSocketClose = function (event) {};

initWebSocket = function (path) {
    ws = new WebSocket(path);
    ws.onopen = onWebSocketOpen;
    ws.onmessage = onWebSocketMessage;
    ws.onclose = onWebSocketClose;
};

initWebSocketRelative = function (appendix) {
    initWebSocket(getWebSocketPath(appendix));
}

function sendWebSocketMessage(message) {
    ws.send(message);
}

function getWebSocketPath(appendix) {
    var host = window.location.host;
    var pathArray = window.location.pathname.split( '/' );
    pathArray.pop();
    return "ws://" + host + pathArray.join("/") + "/" + appendix;
}