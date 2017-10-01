window.onload = performScript;

function performScript() {

    var HttpClient = function() {
        this.request = function(aUrl, aCallback, aMethod, aParams) {

            var method = typeof aMethod === 'undefined' ? "POST" : aMethod;
            var params = typeof aParams === 'undefined' ? null : aParams;

            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() {
                if (anHttpRequest.readyState == 4 && (anHttpRequest.status == 200 || anHttpRequest.status == 304))
                    aCallback(anHttpRequest.responseText);
            };

            anHttpRequest.open(method, aUrl, true);
            anHttpRequest.send(params);
        }
    };

    var startDuelButton = document.getElementById("startDuel");
    var timer = document.getElementById("timer");
    var attackButton = document.getElementById("attack");

    var userHp = document.getElementById("userHp");
    var userDamage = document.getElementById("userDamage");
    var opponentHp = document.getElementById("opponentHp");
    var opponentDamage = document.getElementById("opponentDamage");

    var client = new HttpClient();



    if (startDuelButton !== null) {
        startDuelButton.onclick = function () {

            startDuelButton.innerHTML = "Поиск противника...";
            startDuelButton.disabled = "true";

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

    if (timer !== null) {
        setInterval(function () {
            client.request('/rest/timeBeforeDuel', function(timeBeforeDuel) {
                timer.innerHTML = timeBeforeDuel;
            }, "GET");
        }, 1000);
    }

    if (attackButton !== null) {
        attackButton.onclick = function () {

            client.request('/rest/attack', function(response) {
                console.log("attack: " + response );
            });
        };

        setInterval(function () {
            client.request('/rest/updateCurrentUserInfo', function(currentUserInfo) {

                if (currentUserInfo !== "false") {
                    var parsedInfo = JSON.parse(currentUserInfo);
                    userHp.innerHTML = parsedInfo["hp"];
                }

            }, "GET");

            client.request('/rest/updateOpponentUserInfo', function(opponentUserInfo) {

                if (opponentUserInfo !== "false") {
                    var parsedInfo = JSON.parse(opponentUserInfo);
                    opponentHp.innerHTML = parsedInfo["hp"];
                }
            }, "GET");
        }, 1000);
    }
}