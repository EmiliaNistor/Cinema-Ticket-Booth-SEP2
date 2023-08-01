package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Screen implements Serializable {
    private final int screenId; // You may need to add a screenId field based on your database schema
    private final HashMap<Integer, Movie> movies;
    private final ArrayList<Seat> seats;

    public Screen(int screenId, ArrayList<Seat> seats) {
        this.screenId = screenId;
        this.movies = new HashMap<>();
        this.seats = seats;
    }

    public int getScreenId() {
        return screenId;
    }

    public ArrayList<Movie> getMovies() {
        return new ArrayList<>(movies.values());
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie.getMovieId());
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
}
