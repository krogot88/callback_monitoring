package ru.krogot88.callback_monitoring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + " : get new allcall, number :" + phone + ", b nomer: " + b_number);
    }

}
