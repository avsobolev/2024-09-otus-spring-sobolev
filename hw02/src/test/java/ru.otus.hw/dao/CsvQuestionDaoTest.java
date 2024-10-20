package ru.otus.hw.dao;

import org.junit.jupiter.api.Test;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvQuestionDaoTest {

    @Test
    void shouldReturnAllQuestions() {
        TestFileNameProvider fileNameProviderMock = mock(TestFileNameProvider.class);
        when(fileNameProviderMock.getTestFileName()).thenReturn("questions_test.csv");

        CsvQuestionDao dao = new CsvQuestionDao(fileNameProviderMock);

        List<Question> questions = dao.findAll();

        assertNotNull(questions);
        assertEquals(2, questions.size());

        assertEquals("What is Java?", questions.get(0).text());
        assertEquals("A programming language", questions.get(0).answers().get(1).text());

        assertEquals("What is Spring?", questions.get(1).text());
        assertEquals("A Java framework", questions.get(1).answers().get(1).text());
    }

    @Test
    void shouldThrowExceptionIfFileNotFound() {
        TestFileNameProvider fileNameProviderMock = mock(TestFileNameProvider.class);
        when(fileNameProviderMock.getTestFileName()).thenReturn("non_existent_file.csv");

        CsvQuestionDao dao = new CsvQuestionDao(fileNameProviderMock);

        assertThrows(QuestionReadException.class, dao::findAll);
    }
}