package Models;

public class Ticket {
    private String id;
    private Seat seat;
    private Movie movie;
    private User user;

    public Ticket(String id, Seat seat, Movie movie, User user) {
        this.id = id;
        this.seat = seat;
        this.movie = movie;
        this.user = user;
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

    // Constructors, getters, and setters
}
