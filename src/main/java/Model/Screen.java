package Model;


import java.util.ArrayList;
import java.util.HashMap;

public class Screen {
    private final HashMap<Integer, Movie> movies;
    private final ArrayList<Seat> seats;

    public Screen(ArrayList<Seat> seats) {
        this.movies = new HashMap<>();
        this.seats = new ArrayList<>();
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
