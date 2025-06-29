package ru.otus.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.service.TestRunnerService;


@SpringBootApplication(scanBasePackages = "ru.otus.hw")
@EnableConfigurationProperties(AppProperties.class)
public class Application_hw3 {
    public static void main(String[] args) {
        var context = SpringApplication.run(Application_hw3.class, args);
        context.getBean(TestRunnerService.class).run();
    }
}