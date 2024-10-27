package ru.otus.hw.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.ResultService;
import ru.otus.hw.service.ResultServiceImpl;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.StudentServiceImpl;
import ru.otus.hw.service.TestRunnerServiceImpl;
import ru.otus.hw.service.TestService;
import ru.otus.hw.service.TestServiceImpl;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.otus.hw")
public class SpringConfig {
    @Bean
    TestFileNameProviderImpl testFileNameProvider(@Value("${test.fileName}") String fileName) {
        return new TestFileNameProviderImpl(fileName);
    }

    @Bean
    CsvQuestionDao questionDao(TestFileNameProviderImpl testFileNameProvider) {
        return new CsvQuestionDao(testFileNameProvider);
    }

    @Bean
    TestServiceImpl testService(IOService ioService, QuestionDao questionDao) {
        return new TestServiceImpl(ioService, questionDao);
    }

    @Bean
    StudentServiceImpl studentService(IOService ioService) {
        return new StudentServiceImpl(ioService);
    }

    @Bean
    TestConfigImpl testConfig(@Value("${test.rightAnswersCountToPass}") int rightAnswersCountToPass) {
        return new TestConfigImpl(rightAnswersCountToPass);
    }

    @Bean
    ResultServiceImpl resultService(TestConfig testConfig, IOService ioService) {
        return new ResultServiceImpl(testConfig, ioService);
    }

    @Bean
    TestRunnerServiceImpl testRunnerService(TestService testService,
                                            StudentService studentService,
                                            ResultService resultService) {
        return new TestRunnerServiceImpl(testService, studentService, resultService);
    }
}
