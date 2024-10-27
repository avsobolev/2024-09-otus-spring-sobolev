package ru.otus.hw.service;

import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Test
    void shouldDetermineCurrentStudent() {
        String testFirstName = "Vladimir";
        String testLastName = "Pupkin";

        IOService ioServiceMock = mock(IOService.class);

        when(ioServiceMock.readStringWithPrompt("Please input your first name")).thenReturn(testFirstName);
        when(ioServiceMock.readStringWithPrompt("Please input your last name")).thenReturn(testLastName);

        StudentServiceImpl studentService = new StudentServiceImpl(ioServiceMock);

        Student student = studentService.determineCurrentStudent();

        assertNotNull(student);
        assertEquals(testFirstName, student.firstName());
        assertEquals(testLastName, student.lastName());

        verify(ioServiceMock).readStringWithPrompt("Please input your first name");
        verify(ioServiceMock).readStringWithPrompt("Please input your last name");
    }
}