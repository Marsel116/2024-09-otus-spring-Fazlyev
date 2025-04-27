package ru.otus.hw.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:application.properties")
@Configuration
public class AppProperties implements TestConfig, TestFileNameProvider {

    // внедрить свойство из application.properties
    @Value("${rightAnswersCountToPass}")
    private int rightAnswersCountToPass;

    // внедрить свойство из application.properties
    @Value("${test.fileName}")
    private String testFileName;
}
