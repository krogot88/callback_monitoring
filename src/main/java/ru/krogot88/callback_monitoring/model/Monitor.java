package ru.krogot88.callback_monitoring.model;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.krogot88.callback_monitoring.util.LimitedQueue;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
public class Monitor {

    private MessageSource ms;
    private LimitedQueue<Call> limitedQueue;
    private int minutesBackward;
    private int minutesForward;
    private int[] callsThresholdArray;
    private Alarm alarm;
    private Thread alarmTimer;

    public Monitor(@Qualifier("customMessageSource") MessageSource ms) {
        this.ms = ms;
        limitedQueue = new LimitedQueue<>(Integer.valueOf(ms.getMessage("limitedqueue.size",null, Locale.getDefault())));
        minutesBackward = Integer.valueOf(ms.getMessage("alarm.backward.minutes",null, Locale.getDefault()));
        minutesForward = Integer.valueOf(ms.getMessage("alarm.forward.minutes",null, Locale.getDefault()));
        callsThresholdArray = getCallsThresholdArray(ms.getMessage("call.threshold.hour",null, Locale.getDefault()));
        alarm = new Alarm();
    }

    public void addNewCall(Call call) {
        limitedQueue.add(call);
        //System.out.println(limitedQueue);  - bug on overflow queue toString method
        performAnalitics();
    }

    public Alarm getAlarm() {
        return alarm;
    }

    private void performAnalitics() {
        LocalDateTime now = limitedQueue.getLast().getLocalDateTime();
        LocalDateTime start = now.minusMinutes(minutesBackward);
        int currentCallsThreshold = callsThresholdArray[now.getHour()];
        int callsInPeriod = 0;

        Iterator<Call> it = limitedQueue.descendingIterator();
        while(it.hasNext() && start.compareTo(it.next().getLocalDateTime()) < 0 && callsInPeriod < currentCallsThreshold) {
            callsInPeriod++;
        }

        System.out.println("Threshold: " + currentCallsThreshold + "; calls last: " + callsInPeriod);

        if (callsInPeriod >= currentCallsThreshold) {
            activateAlarm(now);
        }
    }

    private void activateAlarm(LocalDateTime lastCallTime) {
        alarm.setAlarmStatus(AlarmStatus.ON);
        alarm.setAlarmEstimate(lastCallTime.plusMinutes(minutesForward));
        System.out.println("Alarm ON");

        if(alarmTimer != null && alarmTimer.isAlive()) {
            alarmTimer.interrupt();
        }
        alarmTimer = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    TimeUnit.MINUTES.sleep(minutesForward);
                    alarm.setAlarmStatus(AlarmStatus.OFF);
                    alarm.setAlarmEstimate(null);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        alarmTimer.start();
    }

    private static int[] getCallsThresholdArray(String callsThresholdArrayString) {
        String[] stringArr = callsThresholdArrayString.split(";");
        int[] result = new int[24];
        for(int i = 0; i < result.length;i++) {
            String[] subArr = stringArr[i].split("=");
            result[i]=Integer.valueOf(subArr[1]);
        }
        return result;
    }

    public int[] getCallsThresholdArray() {
        return callsThresholdArray;
    }

    public void setCallsThresholdArray(int[] callsThresholdArray) {
        this.callsThresholdArray = callsThresholdArray;
    }
}
