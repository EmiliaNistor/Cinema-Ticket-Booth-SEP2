package Shared.Model;

import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.Ticket;
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
    private Seat seat;
    private Menu menu;

    @BeforeEach
    void setUp() {
        seat = new Seat("C", 22);
        LocalDate date = LocalDate.of(2023, 8, 23);
        LocalTime startTime = LocalTime.of(1,2,3);
        LocalTime endTime = LocalTime.of(3,2,1);
        movie = new Movie(2321, "Oppenheimer", date, startTime, endTime, "biographical thriller", 180, 1);
        menu = new Menu(17, "Popcorn",3.55);
        ticket = new Ticket(1, seat, 2321, 17);
    }

    @AfterEach
    void tearDown() {
        ticket = null;
        movie = null;
        seat = null;
        menu = null;
    }

    @Test
    void getIdTest() {
        assertEquals(1, ticket.getId());
    }

    @Test
    void getSeatTest() {
        assertEquals(seat, ticket.getSeat());
    }

    @Test
    void getMovieTest() {
        assertEquals(movie.getMovieId(), ticket.getMovieId());
    }

    @Test
    void getMenuTest() {
        assertEquals(menu.getMenuId(), ticket.getMenuId());
    }
}