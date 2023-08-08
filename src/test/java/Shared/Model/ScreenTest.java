package Shared.Model;

import Shared.Model.Screen;
import Shared.Model.Seat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {
    private Screen screen;
    private ArrayList<Seat> seats;

    @BeforeEach
    void setUp() {
        seats = new ArrayList<>();
        seats.add(new Seat("D", 7));
        seats.add(new Seat("B", 5));
        screen = new Screen(1, "Screen1", seats);
    }

    @AfterEach
    void tearDown() {
        screen = null;
        seats = null;
    }

    @Test
    void getSeatsTest() {
        assertEquals(seats, screen.getSeats());
    }

    @Test
    void getScreenIdTest() {
        assertEquals(1, screen.getScreenId());
    }
}