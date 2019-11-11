package ru.krogot88.callback_monitoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krogot88.callback_monitoring.model.Alarm;
import ru.krogot88.callback_monitoring.model.Call;
import ru.krogot88.callback_monitoring.model.Monitor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorServiceImpl implements MonitorService {


    @Autowired
    private Monitor monitor;

    @Override
    public void addNewCall(Call call) {
        monitor.addNewCall(call);
    }

    @Override
    public Alarm getAlarm() {
        return monitor.getAlarm();
    }

    @Override
    public List<Integer> getThresholdList() {
        List<Integer> list = Arrays.stream(monitor.getCallsThresholdArray()).boxed().collect(Collectors.toList());
        return list;
    }
}
