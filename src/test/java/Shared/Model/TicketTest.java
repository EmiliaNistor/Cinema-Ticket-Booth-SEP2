package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
        movie = new Movie(2321, "Oppenheimer", date, "biographical thriller", 180);
        screen = new Screen(new ArrayList<>(), 3);
        ticket = new Ticket(1, seat, movie, screen);
        menu = new Menu(17, "Popcorn",3.55);
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
        assertEquals(movie, ticket.getMovie());
    }

    @Test
    void getScreen() {
        assertEquals(screen, ticket.getScreen());
    }

    @Test
    void getMenus() {
        assertTrue(ticket.getMenus().isEmpty());
    }

    @Test
    void addMenu() {
        ticket.addMenu(menu);
        assertTrue(ticket.getMenus().contains(menu));
    }

    @Test
    void removeMenu() {
        ticket.addMenu(menu);
        ticket.removeMenu(menu);
        assertFalse(ticket.getMenus().contains(menu));
    }
}