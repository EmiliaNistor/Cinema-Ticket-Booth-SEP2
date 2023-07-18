package Shared;

import Model.Menu;
import Model.Movie;
import Model.Seat;
import Model.User;

public class Ticket {
    private String id;
    private Seat seat;
    private Movie movie;
    private User user;
    private Menu menu;

    public Ticket(String id, Seat seat, Movie movie, User user, Menu menu) {
        this.id = id;
        this.seat = seat;
        this.movie = movie;
        this.user = user;
        this.menu = menu;
    }

    public Ticket() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    // Constructors, getters, and setters
}
