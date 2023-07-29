package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {
    private Screen screen;
    private ArrayList<Seat> seats;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp() {
        seats = new ArrayList<>();
        seats.add(new Seat("D", 7));
        seats.add(new Seat("B", 5));
        screen = new Screen(seats, 1);
        LocalDate date1 = LocalDate.of(2023, 8, 23);
        movie1 = new Movie(1, "Barbie", date1, "comedy", 163);
        LocalDate date2 = LocalDate.of(2023, 8, 22);
        movie2 = new Movie(2, "Oppenheimer", date2, "biographical thriller", 180);
    }

    @AfterEach
    void tearDown() {
        screen = null;
        seats = null;
        movie1 = null;
        movie2 = null;
    }

    @Test
    void getMovies() {
        assertTrue(screen.getMovies().isEmpty());
    }

    @Test
    void addMovie() {
        screen.addMovie(movie1);
        assertEquals(1, screen.getMovies().size());
        assertTrue(screen.getMovies().contains(movie1));
    }

    @Test
    void removeMovie() {
        screen.addMovie(movie1);
        screen.addMovie(movie2);

        assertEquals(2, screen.getMovies().size());

        screen.removeMovie(movie1);

        assertEquals(1, screen.getMovies().size());
        assertFalse(screen.getMovies().contains(movie1));
        assertTrue(screen.getMovies().contains(movie2));
    }

    @Test
    void getSeats() {
        assertEquals(seats, screen.getSeats());
    }

    @Test
    void getScreenId() {
        assertEquals(1, screen.getScreenId());
    }
}