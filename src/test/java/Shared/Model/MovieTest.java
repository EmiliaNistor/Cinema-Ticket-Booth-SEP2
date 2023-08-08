package Shared.Model;

import Shared.Model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie movie;


    @BeforeEach
    void setUp() {
        movie = new Movie(1, "Oppenheimer",
                LocalDate.of(2023, 7, 28), LocalTime.of(1,2,3), LocalTime.of(3,2,1),
                "Action", 120, 1);
    }

    @AfterEach
    void tearDown() {
        movie = null;
    }

    @Test
    void getMovieIdTest() {
        assertEquals(1, movie.getMovieId());
    }

    @Test
    void getNameTest() {
        assertEquals("Oppenheimer", movie.getName());
    }

    @Test
    void setNameTest() {
        movie.setName("Barbie");
        assertEquals("Barbie", movie.getName());
    }

    @Test
    void getDateTest() {
        assertEquals(LocalDate.of(2023, 7, 28), movie.getDate());
    }

    @Test
    void setDateTest() {
        movie.setDate(LocalDate.of(2023, 10, 30));
        assertEquals(LocalDate.of(2023, 10, 30), movie.getDate());
    }

    @Test
    void getLengthTest() {
        assertEquals(120, movie.getLength());
    }

    @Test
    void setLengthTest() {
        movie.setLength(129);
        assertEquals(129, movie.getLength());
    }

    @Test
    void getGenreTest() {
        assertEquals("Action", movie.getGenre());
    }

    @Test
    void setGenreTest() {
        movie.setGenre("Action");
        assertEquals("Action", movie.getGenre());
    }

    @Test
    void getScreenIdTest() {
        assertEquals(1, movie.getScreenId());
    }

    @Test
    void getStartTimeTest() {
        assertEquals(LocalTime.of(1,2,3), movie.getStartTime());
    }

    @Test
    void setStartTimeTest() {
        movie.setStartTime(LocalTime.of(11,22,33));
        assertEquals(LocalTime.of(11,22,33), movie.getStartTime());
    }

    @Test
    void getEndTimeTest() {
        assertEquals(LocalTime.of(3,2,1), movie.getEndTime());
    }

    @Test
    void setEndTimeTest() {
        movie.setEndTime(LocalTime.of(11,22,33));
        assertEquals(LocalTime.of(11,22,33), movie.getEndTime());
    }
}