window.onload = performScript;

function performScript() {

    var HttpClient = function() {
        this.request = function(aUrl, aCallback, aMethod, aParams) {

            var method = typeof aMethod === 'undefined' ? "POST" : aMethod;
            var params = typeof aParams === 'undefined' ? null : aParams;

            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() {
                if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                    aCallback(anHttpRequest.responseText);
            };

            anHttpRequest.open(method, aUrl, true);
            anHttpRequest.send(params);
        }
    };

    var startDuelButton = document.getElementById("startDuel");


    if (startDuelButton !== null) {
        startDuelButton.onclick = function () {

            startDuelButton.innerHTML = "Поиск противника...";
            startDuelButton.disabled = "true";

            var client = new HttpClient();
            client.request('/rest/onConnect', function(username) {
                setInterval(function () {
                    // console.log(username);
                    client.request('/rest/isRoomFilled', function(isRoomFilled) {
                        console.log(isRoomFilled);
                        if (isRoomFilled === "true") {
                            window.location.href = "/preparing";
                        }
                    }, "GET");
                }, 1000);
            });
        };
    }
}