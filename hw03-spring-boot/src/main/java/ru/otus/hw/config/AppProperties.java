package ru.otus.hw.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@Setter
@ConfigurationProperties("ru.otus.hw")
public class AppProperties implements TestConfig, TestFileNameProvider {

    @Getter
    private int rightAnswersCountToPass;

    @Getter
    private Boolean starterProviderEnabled;

    @Getter
    private Locale locale;

    @Getter
    private Map<String, String> fileNameByLocaleTag;

    public void setLocale(String locale) {
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public String getTestFileName() {
        return fileNameByLocaleTag.get(locale.toLanguageTag());
    }
}
