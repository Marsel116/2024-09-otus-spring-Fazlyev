package ru.otus.hw.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.service.LocalizedMessagesService;
import ru.otus.starter.config.LocaleProvider;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {
    private final LocaleProvider provider;
    private final MessageSource messageSource;


    @Override
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, provider.getLocale());
    }
}
