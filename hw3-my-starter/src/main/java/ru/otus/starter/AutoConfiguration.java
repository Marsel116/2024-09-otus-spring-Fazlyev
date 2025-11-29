package ru.otus.starter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.starter.config.LocaleProvider;
import ru.otus.starter.config.RussianLocaleProvider;

@Configuration
@ConditionalOnProperty(value = "ru.otus.hw.starter-provider-enabled", havingValue = "true", matchIfMissing = true)
public class AutoConfiguration {

    @ConditionalOnMissingBean(LocaleProvider.class)
    @Bean
    public LocaleProvider localeProvider(@Value("${ru.otus.hw.locale}") String localeTag) {
        return new RussianLocaleProvider(localeTag);
    }
}
