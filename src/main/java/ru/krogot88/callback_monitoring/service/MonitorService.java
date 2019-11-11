package ru.krogot88.callback_monitoring.service;

import ru.krogot88.callback_monitoring.model.Alarm;
import ru.krogot88.callback_monitoring.model.Call;

import java.util.List;

public interface MonitorService {

    void addNewCall(Call call);

    Alarm getAlarm();

    List<Integer> getThresholdList();

}
