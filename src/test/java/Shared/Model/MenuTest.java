package Shared.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu(1, "Nachos with drink", 8.30);
    }

    @AfterEach
    void tearDown() {
        menu = null;
    }

    @Test
    void getMenuId() {
        assertEquals(1, menu.getMenuId());
    }

    @Test
    void getFood() {
        assertEquals("Nachos with drink", menu.getFood());
    }

    @Test
    void setFood() {
        menu.setFood("Food");
        assertEquals("Food", menu.getFood());
    }

    @Test
    void getPrice() {
        assertEquals(8.30, menu.getPrice(), 0.001);
    }

    @Test
    void setPrice() {
        menu.setPrice(18.0);
        assertEquals(18.0, menu.getPrice(), 0.001);
    }

}