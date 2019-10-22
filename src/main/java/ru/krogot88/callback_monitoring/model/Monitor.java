package ru.krogot88.callback_monitoring.model;


import ru.krogot88.callback_monitoring.util.LimitedQueue;

public class Monitor {
    private LimitedQueue<Call> limitedQueue;
    private int backTime;
    private int callsCriticalThreshold;

    public Monitor() {
        limitedQueue = new LimitedQueue<>(30);
        backTime = 15;
        callsCriticalThreshold = 5;
    }


}
