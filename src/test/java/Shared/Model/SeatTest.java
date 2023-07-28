package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {
    private Seat seat;

    @BeforeEach
    void setUp() {
        seat = new Seat("F", 1);
    }

    @AfterEach
    void tearDown() {
        seat = null;
    }

    @Test
    void getRow() {
        assertEquals("F", seat.getRow());
    }

    @Test
    void getNumber() {
        assertEquals(1, seat.getNumber());
    }
}