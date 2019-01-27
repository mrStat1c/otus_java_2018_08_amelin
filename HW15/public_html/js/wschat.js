var ws;

init = function () {
    ws = new WebSocket("ws://localhost:8090/chat");
//    далее идут обработчики событий
    ws.onopen = function (event) {

    }
    //если пришло сообщение с сервера..
    ws.onmessage = function (event) {
        var $textarea = document.getElementById("messages");
        $textarea.value = $textarea.value + event.data + "\n";
    }
    ws.onclose = function (event) {

    }
};
//если мы захотели отправить сообщение (клиентская сторона)
function sendMessage() {
    var messageField = document.getElementById("message");
    var message = messageField.value;
    ws.send(message);
    messageField.value = '';
}