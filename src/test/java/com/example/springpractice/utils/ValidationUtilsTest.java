package com.example.springpractice.utils;

import com.example.springpractice.entites.Mentor;
import com.example.springpractice.entites.Session;
import com.example.springpractice.entites.Student;
import com.example.springpractice.exception.SkillMentorException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Import all assertions
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ValidationUtilsTest {

    private Date createDate(int year, int month, int day, int hour, int minute) {
        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Test
    void isTimeOverlap_ReturnsTrue_WhenTimesOverlap() {
        Date start1 = createDate(2023, 1, 1, 10, 0);
        Date end1 = createDate(2023, 1, 1, 11, 0);
        Date start2 = createDate(2023, 1, 1, 10, 30);
        Date end2 = createDate(2023, 1, 1, 11, 30);

        // Fixed typo: assertTru -> assertTrue
        assertTrue(ValidationUtils.isTimeOverlap(start1, end1, start2, end2));
    }

    @Test
    void isTimeOverlap_ReturnsFalse_WhenTimesDoNotOverlap() {
        Date start1 = createDate(2023, 1, 1, 10, 0);
        Date end1 = createDate(2023, 1, 1, 11, 0);
        Date start2 = createDate(2023, 1, 1, 11, 0);
        Date end2 = createDate(2023, 1, 1, 12, 0);

        assertFalse(ValidationUtils.isTimeOverlap(start1, end1, start2, end2));
    }

    @Test
    void addMinutesToDate_AddsCorrectMinutes() {
        Date start = createDate(2023, 1, 1, 10, 0);
        Date expected = createDate(2023, 1, 1, 10, 30);

        Date result = ValidationUtils.addMinutesToDate(start, 30);

        assertEquals(expected, result);
    }

    @Test
    void validateMentorAvailability_ThrowsException_WhenOverlapExists() {
        Mentor mentor = new Mentor();
        List<Session> sessions = new ArrayList<>();
        Session existingSession = new Session();
        existingSession.setSessionAt(createDate(2023, 1, 1, 10, 0));
        existingSession.setDurationMinutes(60);
        sessions.add(existingSession);
        mentor.setSessions(sessions);

        Date newSessionAt = createDate(2023, 1, 1, 10, 30);

        assertThrows(SkillMentorException.class, () ->
                ValidationUtils.validateMentorAvailability(mentor, newSessionAt, 60)
        );
    }
}