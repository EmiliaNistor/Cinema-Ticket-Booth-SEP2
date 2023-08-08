package Shared.Model;

import Shared.Model.Menu;
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
    void getMenuIdTest() {
        assertEquals(1, menu.getMenuId());
    }

    @Test
    void getFoodTest() {
        assertEquals("Nachos with drink", menu.getFood());
    }

    @Test
    void setFoodTest() {
        menu.setFood("Food");
        assertEquals("Food", menu.getFood());
    }

    @Test
    void setFoodNullTest() {
        menu.setFood(null);
        assertEquals(null, menu.getFood());
    }

    @Test
    void getPriceTest() {
        assertEquals(8.30, menu.getPrice(), 0.001);
    }

    @Test
    void setPriceTest() {
        menu.setPrice(18.0);
        assertEquals(18.0, menu.getPrice(), 0.001);
    }

}