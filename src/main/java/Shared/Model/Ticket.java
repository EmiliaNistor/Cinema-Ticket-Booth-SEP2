package Shared.Model;

import java.io.Serializable;

/**
 * Holds information about the ticket
 */
public class Ticket implements Serializable
{
    private final int id;
    private final Seat seat;
    private final int movieId;
    private final int menuId;

    public Ticket(int id, Seat seat, int movieId, int menuId) {
        this.id = id;
        this.seat = seat;
        this.movieId = movieId;
        this.menuId = menuId;
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

    /**
     * Get the movie id the ticket is for
     * @return The movie's id
     */
    public int getMovieId() {
        return movieId;
    }

    /**
     * Get the menu id, if chosen
     * @return The menu id chosen with the movie, -1 if none
     */
    public int getMenuId() {
        return menuId;
    }
}
