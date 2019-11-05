package ru.krogot88.callback_monitoring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.krogot88.callback_monitoring.model.Alarm;
import ru.krogot88.callback_monitoring.model.AlarmDTO;
import ru.krogot88.callback_monitoring.model.Call;
import ru.krogot88.callback_monitoring.service.MonitorService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping(value = "/callback")
    @ResponseStatus(value = HttpStatus.OK)
    public void getCallback(@RequestParam(name = "phone",defaultValue = "0") String phone, @RequestParam(name = "b_number",defaultValue = "0") String b_number) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + " : get new callback, number :" + phone + ", b nomer: " + b_number);
    }

    @GetMapping(value = "/allcall")
    @ResponseStatus(value = HttpStatus.OK)
    public void getAllCall(@RequestParam(name = "phone",defaultValue = "0") String phone, @RequestParam(name = "b_number",defaultValue = "0") String b_number) {
        LocalDateTime now = LocalDateTime.now();
        Call call = new Call();
        call.setLocalDateTime(now);
        call.setaNumber(phone);
        call.setbNumber(b_number);
        monitorService.addNewCall(call);
    }

    @GetMapping(value = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value = "/alarm")
    public ResponseEntity<AlarmDTO> getAlarm() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime alarmEstimate = now;
        if(monitorService.getAlarm().getAlarmEstimate() != null) {
            alarmEstimate = monitorService.getAlarm().getAlarmEstimate();
        }
        Duration duration = Duration.between(now,alarmEstimate);
        AlarmDTO alarmDTO = new AlarmDTO(monitorService.getAlarm().getAlarmStatus().toString(),duration.getSeconds());
        return new ResponseEntity<>(alarmDTO,HttpStatus.OK);
    }
}
