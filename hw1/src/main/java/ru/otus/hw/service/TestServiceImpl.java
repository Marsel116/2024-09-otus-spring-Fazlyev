package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");

        ioService.printFormattedLine("Please answer the questions below%n");

        List<Question> questionList = questionDao.findAll();

        System.out.println(questionList);

        //toDo прописать метод pretty print в questionDao
        questionList.forEach(question -> System.out.println("Вопрос: " + question.text() + "\n" + " Возможные варианты ответа: " + question.answers().stream().map(Answer::text).toList()));
        // Получить вопросы из дао и вывести их с вариантами ответов
    }
}
