package ru.krogot88.callback_monitoring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.krogot88.callback_monitoring.model.Monitor;

@Configuration
public class AppConfig {
    @Bean
    public Monitor getMonitor() {
        return new Monitor();
    }
}