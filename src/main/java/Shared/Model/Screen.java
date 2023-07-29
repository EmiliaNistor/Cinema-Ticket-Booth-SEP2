package Shared.Model;


import java.util.ArrayList;
import java.util.HashMap;

public class Screen {
    private final HashMap<Integer, Movie> movies;
    private final ArrayList<Seat> seats;
    private int screenId;

    public Screen(ArrayList<Seat> seats, int screenId) {
        this.movies = new HashMap<>();
        this.seats = new ArrayList<>();
        this.screenId = screenId;
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

    public int getScreenId() {
        return screenId;
    }
}
