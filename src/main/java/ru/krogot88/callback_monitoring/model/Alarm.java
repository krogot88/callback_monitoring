package ru.krogot88.callback_monitoring.model;

import java.time.LocalDateTime;

public class Alarm {

    private enum AlarmStatus {
        ON,
        OFF
    }
    private AlarmStatus alarmStatus;
    private LocalDateTime alarmEstimate;

    public Alarm() {
    }

    public AlarmStatus getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(AlarmStatus alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public LocalDateTime getAlarmEstimate() {
        return alarmEstimate;
    }

    public void setAlarmEstimate(LocalDateTime alarmEstimate) {
        this.alarmEstimate = alarmEstimate;
    }
}
