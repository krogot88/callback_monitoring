package ru.krogot88.callback_monitoring.model;


public class AlarmDTO {
    private String alarmStatus;
    private long duration;

    public AlarmDTO(String alarmStatus, long duration) {
        this.alarmStatus = alarmStatus;
        this.duration = duration;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
