package ru.otus.hw.config;

public class TestFileNameProviderImpl implements TestFileNameProvider {
    private final String fileName;

    public TestFileNameProviderImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getTestFileName() {
        return fileName;
    }
}