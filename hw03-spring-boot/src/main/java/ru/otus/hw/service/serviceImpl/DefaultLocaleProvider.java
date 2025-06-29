package ru.otus.hw.service.serviceImpl;

import ru.otus.starter.config.LocaleProvider;

import java.util.Locale;

public class DefaultLocaleProvider implements LocaleProvider {

    public DefaultLocaleProvider(String localeTag) {
        Locale locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
