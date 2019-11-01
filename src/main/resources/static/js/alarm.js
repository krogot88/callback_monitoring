window.onload = function () {
    checkAlarm();
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
                activateAlarm(data.alarmEstimate);
            } else {
                timer();
            }
        }
    });
}

function activateAlarm(alarmEstimate) {
    $(".alarm_span").toggleClass("red");
    document.getElementById('player').play();
    document.getElementById("button_alarm_off").hidden = false;
    //document.getElementById('player').volume+=0.1
    //document.getElementById('player').volume-=0.1
    setTimeout(function () {
        $(".alarm_span").toggleClass("red");
        document.getElementById('player').pause();
        document.getElementById("button_alarm_off").hidden = true;
        checkAlarm();
    },70000);
}

function stopMusic() {
    document.getElementById('player').pause();
    document.getElementById("button_alarm_off").hidden = true;
}

function timer() {
    setTimeout(checkAlarm,30000);
}
