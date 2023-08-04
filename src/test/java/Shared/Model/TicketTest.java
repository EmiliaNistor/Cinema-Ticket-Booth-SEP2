package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private Ticket ticket;
    private Movie movie;
    private Screen screen;
    private Seat seat;
    private Menu menu;

    @BeforeEach
    void setUp() {
        seat = new Seat("C", 22);
        LocalDate date = LocalDate.of(2023, 8, 23);
        LocalTime startTime = LocalTime.of(1,2,3);
        LocalTime endTime = LocalTime.of(3,2,1);
        movie = new Movie(2321, "Oppenheimer", date, startTime, endTime, "biographical thriller", 180, 1);
        screen = new Screen(1, "IMAX", new ArrayList<>());
        menu = new Menu(17, "Popcorn",3.55);
        ticket = new Ticket(1, seat, 2321, 1, 17);
    }

    @AfterEach
    void tearDown() {
        ticket = null;
        movie = null;
        screen = null;
        seat = null;
        menu = null;
    }

    @Test
    void getId() {
        assertEquals(1, ticket.getId());
    }

    @Test
    void getSeat() {
        assertEquals(seat, ticket.getSeat());
    }

    @Test
    void getMovie() {
        assertEquals(movie.getMovieId(), ticket.getMovieId());
    }

    @Test
    void getScreen() {
        assertEquals(screen.getScreenId(), ticket.getScreenId());
    }

    @Test
    void getMenu() {
        assertEquals(menu.getMenuId(), ticket.getMenuId());
    }
}