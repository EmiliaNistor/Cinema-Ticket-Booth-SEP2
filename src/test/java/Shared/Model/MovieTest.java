package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie movie;


    @BeforeEach
    void setUp() {
        movie = new Movie(1, "Oppenheimer", LocalDate.of(2023, 7, 28), "Action", 120);
    }

    @AfterEach
    void tearDown() {
        movie = null;
    }

    @Test
    void getMovieId() {
        assertEquals(1, movie.getMovieId());
    }

    @Test
    void getName() {
        assertEquals("Oppenheimer", movie.getName());
    }

    @Test
    void setName() {
        movie.setName("Barbie");
        assertEquals("Barbie", movie.getName());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2023, 7, 28), movie.getDate());
    }

    @Test
    void setDate() {
        movie.setDate(LocalDate.of(2023, 10, 30));
        assertEquals(LocalDate.of(2023, 10, 30), movie.getDate());
    }

    @Test
    void getLength() {
        assertEquals(120, movie.getLength());

    }

    @Test
    void setLength() {
        movie.setLength(129);
        assertEquals(129, movie.getLength());
    }

    @Test
    void getGenre() {
        assertEquals("Action", movie.getGenre());
    }

    @Test
    void setGenre() {
        movie.setGenre("Action");
        assertEquals("Action", movie.getGenre());
    }
}