package ru.otus.hw.config;

public class TestConfigImpl implements TestConfig {
        private int rightAnswersCountToPass;

    public TestConfigImpl(int rightAnswersCountToPass) {
        this.rightAnswersCountToPass = rightAnswersCountToPass;
    }

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }
}
