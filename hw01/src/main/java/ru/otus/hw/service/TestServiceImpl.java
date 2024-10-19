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
        // Получить вопросы из дао и вывести их с вариантами ответов

        List<Question> questions = questionDao.findAll();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            ioService.printFormattedLine("Question №%d: %s", i + 1, question.text());
            printAnswers(question.answers());
            ioService.printLine("");
        }
    }

    private void printAnswers(List<Answer> answers) {
        ioService.printLine("Aswers: ");
        int answerCount = 0;
        for (Answer answer : answers) {
            answerCount++;
            ioService.printFormattedLine("   %d. %s", answerCount, answer.text());
        }
    }
}
