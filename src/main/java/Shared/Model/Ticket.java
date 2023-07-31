package Shared.Model;

import java.util.ArrayList;

/**
 * Holds information about the ticket
 */
public class Ticket {
    private final int id;
    private final Seat seat;
    private final Movie movie;
    private final Screen screen;
    private final Menu menu;

    public Ticket(int id, Seat seat, Movie movie, Screen screen, Menu menu) {
        this.id = id;
        this.seat = seat;
        this.movie = movie;
        this.screen = screen;
        this.menu = menu;
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

    public Screen getScreen() {
        return screen;
    }

    public Menu getMenu() {
        return menu;
    }
}
