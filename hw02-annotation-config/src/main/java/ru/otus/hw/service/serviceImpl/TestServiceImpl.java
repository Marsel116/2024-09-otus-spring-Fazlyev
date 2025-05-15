package ru.otus.hw.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.TestService;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question : questions) {
            var isAnswerValid = false;
            // Задать вопрос, получить ответ
            ioService.printFormattedLine(question.text());
            ioService.printFormattedLine("Available answers:%n");

            var answersText = question.answers().stream().map(Answer::text).toList();
            answersText.forEach(ioService::printFormattedLine);

            var targetAnswer = ioService.readString();

            var gotAnswer = question.answers().stream()
                    .filter(an -> an.text().equals(targetAnswer))
                    .findFirst();

            if (gotAnswer.isPresent()) {
                var answer = gotAnswer.get();
                isAnswerValid = answer.isCorrect();
            }

            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
