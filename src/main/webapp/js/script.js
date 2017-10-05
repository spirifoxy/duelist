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
    var logsBox = document.getElementById("log");

    var resultsModal = document.getElementById("resultsModal");
    var closeResultsModal = document.getElementById("closeResultsModal");

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
                    client.request('/rest/isRoomFilled', function(isRoomFilled) {
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
                timer.innerHTML = timeBeforeDuel; //TODO
            }, "GET");
        }, 1000);
    }

    if (attackButton !== null) {

        closeResultsModal.onclick = function() {
            resultsModal.style.display = "none";
        };

        //check current turn
        //if it's ours - wait for attack button is clicked
        //if it's not - listen to updates
        client.request('/rest/isNowCurrentUserTurn', function(response) {
            if (response === "true") {
                attackButton.disabled = false;
            } else {
                attackButton.disabled = true;
                listenForCurrentUserUpdates();
            }
        }, "GET");

        attackButton.onclick = function () {
            attackButton.disabled = true;

            client.request('/rest/attack', function(response) {
                if (response === "true") { //if user has attacked opponent successfully

                    client.request('/rest/updateUserInfo/?type=opponent', function(opponentUserInfo) {
                        var isGameFinished = "";
                        if (opponentUserInfo !== "false") {
                            var parsedInfo = JSON.parse(opponentUserInfo);

                            opponentHp.innerHTML = parsedInfo["hp"];
                            opponentHp.style.width = parsedInfo["hp"] ? (parsedInfo["hp"] * 100 / parsedInfo["maxHp"]) : 0 + "%";

                            logsBox.innerHTML = "<br>Вы ударили " + parsedInfo["opponent"] + " на " + parsedInfo["damage"] + " урона" + logsBox.innerHTML ;

                            isGameFinished = parsedInfo["isLastTurn"];
                        }

                        if (isGameFinished !== true) {
                            listenForCurrentUserUpdates();
                        } else {
                            client.request('/rest/updateUsersAfterGame', function(isCurrentUserWinner) {
                                logsBox.innerHTML = "<br>Вы убили " + parsedInfo["opponent"] + logsBox.innerHTML ;

                                resultsModal.getElementsByClassName("result")[0].innerHTML = "Победа!";
                                resultsModal.style.display = "block";

                            });
                        }
                    }, "GET");
                } else {
                    //error during attack handling
                }
            });
        };

        var listenForCurrentUserUpdates = function () {
            var updateListener = setInterval(function () {
                client.request('/rest/updateUserInfo/?type=user', function(currentUserInfo) {

                    var isGameFinished = "";
                    if (currentUserInfo !== "false") {
                        var parsedInfo = JSON.parse(currentUserInfo);

                        userHp.innerHTML = parsedInfo["hp"];
                        userHp.style.width = parsedInfo["hp"] ? (parsedInfo["hp"] * 100 / parsedInfo["maxHp"]) : 0 + "%";

                        logsBox.innerHTML = "<br>" + parsedInfo["opponent"] + " ударил вас на " + parsedInfo["damage"] + " урона" + logsBox.innerHTML ;

                        window.clearInterval(updateListener);
                        isGameFinished = parsedInfo["isLastTurn"];
                        if (isGameFinished === true) {
                            attackButton.disabled = true;
                            logsBox.innerHTML = "<br>Вы были убиты " + parsedInfo["opponent"] + logsBox.innerHTML ;
                            client.request('/rest/updateUsersAfterGame', function(isCurrentUserWinner) {
                                resultsModal.getElementsByClassName("result")[0].innerHTML = "Поражение";
                                resultsModal.style.display = "block";
                            });
                        } else {
                            attackButton.disabled = false;
                        }
                    }
                }, "GET");
            }, 1000);
        };


    }
}