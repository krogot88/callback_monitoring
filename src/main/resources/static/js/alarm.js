var globalDuration = 30;
var displayAlarm = document.getElementById("timer");
var alarmStatus = false;

window.onload = function () {
    checkAlarm();
}

function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var intervalID = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            checkAlarm();
            clearInterval(intervalID);
        }
    }, 1000);
}

function checkAlarm() {
    $.ajax({
        url: "/alarm",
        datatype: "json",
        type: "get",
        contentType: "application/json",
        success: function (data) {
            var status = data.alarmStatus;
            $(".alarm_span").text(status);
            if(status == "ON") {
                activateAlarm(data.duration);
                startTimer(globalDuration, displayAlarm);
            } else {
                deactivateAlarm();
                startTimer(globalDuration, displayAlarm);
            }
        },
        error: function(request, status, err) {
                serverTimeout();
                startTimer(globalDuration, displayAlarm);
        }
    });
}

function activateAlarm(alarmEstimateSeconds) {
    var alarmSpan = document.querySelector('.alarm_span');
    alarmSpan.id="red";
    document.getElementById('player').play();
    document.getElementById("button_alarm_off").hidden = false;
    globalDuration = alarmEstimateSeconds;
    alarmStatus = true;
}

function deactivateAlarm() {
    var alarmSpan = document.querySelector('.alarm_span');
    alarmSpan.id="green";
    if(alarmStatus == true) {
        document.getElementById('player').pause();
        document.getElementById("button_alarm_off").hidden = true;
        globalDuration = 30;
        alarmStatus = false;
    }
}

function serverTimeout() {
    var alarmSpan = document.querySelector('.alarm_span');
    alarmSpan.id="orange";
    globalDuration = 30;
}

function stopMusic() {
    document.getElementById('player').pause();
    document.getElementById("button_alarm_off").hidden = true;
}
