package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);
        int questionCount = 0;
        int answerIndex;

        for (var question: questions) {
            questionCount++;
            printQuestionAndAnswers(questionCount, question);
            answerIndex = getAnswerIndex(question);
            var isAnswerValid = isAnswerValid(question, answerIndex);
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    private void printQuestionAndAnswers(int questionNumber, Question question) {
        ioService.printFormattedLine("Question №%d: %s", questionNumber, question.text());
        int answerCount = 0;
        for (Answer answer : question.answers()) {
            ioService.printFormattedLine("   %d. %s", answerCount, answer.text());
            answerCount++;
        }
    }

    private int getAnswerIndex(Question question) {
        return ioService.readIntForRange(0, question.answers().size() - 1,
                "The answer is not correct. Pleas try again");
    }

    private boolean isAnswerValid(Question question, int answerIndex) {
        return question.answers().get(answerIndex).isCorrect();
    }

}
