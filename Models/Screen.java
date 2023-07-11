package Models;


import java.awt.List;
import java.util.ArrayList;

public class Screen {
    private ArrayList<Movie> movies;
    private ArrayList<Seat> seats;

    public Screen(ArrayList<Movie> movies, ArrayList<Seat> seats) {
        this.movies = movies;
        this.seats = seats;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }


}
