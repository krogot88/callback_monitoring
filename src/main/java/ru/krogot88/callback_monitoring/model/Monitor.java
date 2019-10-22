package ru.krogot88.callback_monitoring.model;


import ru.krogot88.callback_monitoring.util.LimitedQueue;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Monitor {
    private LimitedQueue<Call> limitedQueue;
    private int backTimeMinutes;
    private int estimateTimeMinutes;
    private int callsThreshold;
    private Alarm alarm;
    private Thread thread;

    public Monitor() {
        limitedQueue = new LimitedQueue<>(5);
        backTimeMinutes = 2;
        estimateTimeMinutes = 1;
        callsThreshold = 3;
        alarm = new Alarm();
    }

    public void addNewCall(Call call) {
        limitedQueue.add(call);
        System.out.println(limitedQueue);
        performAnalitics();
    }

    public Alarm getAlarm() {
        return alarm;
    }

    private void performAnalitics() {
        LocalDateTime now = limitedQueue.getLast().getLocalDateTime();
        LocalDateTime start = now.minusMinutes(backTimeMinutes);
        LocalDateTime currentCall = now;
        int callsInPeriod = 0;

        Iterator<Call> it = limitedQueue.descendingIterator();
        while(it.hasNext() && start.compareTo(it.next().getLocalDateTime()) < 0 && callsInPeriod < callsThreshold) {
            callsInPeriod++;
        }

        if (callsInPeriod >= callsThreshold) {
            alarm.setAlarmStatus(AlarmStatus.ON);
            alarm.setAlarmEstimate(now.plusMinutes(estimateTimeMinutes));
            System.out.println("Alarm ON");


            thread = new Thread(new Runnable() {
                @Override
                public void run(){
                    try {
                        TimeUnit.SECONDS.sleep(estimateTimeMinutes);
                        alarm.setAlarmStatus(AlarmStatus.OFF);
                        alarm.setAlarmEstimate(null);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
        }
    }
}
