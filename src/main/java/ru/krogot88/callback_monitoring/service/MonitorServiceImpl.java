package ru.krogot88.callback_monitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.krogot88.callback_monitoring.model.Alarm;
import ru.krogot88.callback_monitoring.model.Call;
import ru.krogot88.callback_monitoring.model.Monitor;

public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private Monitor monitor;

    @Override
    public void addNewCall(Call call) {

    }

    @Override
    public Alarm getAlarm() {
        return null;
    }
}
