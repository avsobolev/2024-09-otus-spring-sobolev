package ru.otus.hw;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.hw.config.SpringConfig;
import ru.otus.hw.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {
        //Создать контекст на основе Annotation/Java конфигурирования
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();
    }
}