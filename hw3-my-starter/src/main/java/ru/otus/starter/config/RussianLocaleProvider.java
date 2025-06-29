package ru.otus.starter.config;

import java.util.Locale;

public class RussianLocaleProvider implements LocaleProvider {

    private final Locale locale;

    public RussianLocaleProvider(String localeTag) {
        this.locale = Locale.forLanguageTag(localeTag);
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }
}
