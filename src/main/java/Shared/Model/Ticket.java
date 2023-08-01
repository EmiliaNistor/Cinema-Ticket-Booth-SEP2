package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds information about the ticket
 */
public class Ticket implements Serializable
{
    private final int id;
    private final Seat seat;
    private final Movie movie;
    private final Screen screen;
    private final ArrayList<Menu> menus;

    public Ticket(int id, Seat seat, Movie movie, Screen screen, Menu menu) {
        this.id = id;
        this.seat = seat;
        this.movie = movie;
        this.screen = screen;
        this.menus = new ArrayList<>();
    }

    /**
     * Gets the id of the ticket
     * @return The id of the ticket
     */
    public int getId() {
        return id;
    }

    /**
     * The seat of the ticket
     * @return Seat
     */
    public Seat getSeat() {
        return seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }
}
