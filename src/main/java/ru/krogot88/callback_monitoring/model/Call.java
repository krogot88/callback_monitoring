package ru.krogot88.callback_monitoring.model;

import java.time.LocalDateTime;

public class Call {
    private LocalDateTime localDateTime;
    private String aNumber;
    private String bNumber;

    public Call() {
    }

    public Call(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Call(LocalDateTime localDateTime, String aNumber) {
        this.localDateTime = localDateTime;
        this.aNumber = aNumber;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public String getbNumber() {
        return bNumber;
    }

    public void setbNumber(String bNumber) {
        this.bNumber = bNumber;
    }
}
