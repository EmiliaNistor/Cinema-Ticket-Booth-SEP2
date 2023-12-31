package Client.Model;

import Shared.Model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IMovieListModel
{
    /**
     * Refreshes the available list of movies
     */
    void refreshMovies();

    /**
     * Get all the movies that are equal to provided movie and occur on the same date
     * @param movie The movie to match with
     * @param date The date to match with
     * @return Movies which are equal to provided movie and occur on the same date
     */
    ArrayList<Movie> getSameMoviesByDate(Movie movie, LocalDate date);

    /**
     * Get all the movies that are equal to provided movie
     * @param movie The movie to match with
     * @return Movies that are equal
     */
    ArrayList<Movie> getSameMovies(Movie movie);

    /**
     * Get movie by its id
     * @param movieId The movie's id
     * @return Movie, null if not found
     */
    Movie getMovieById(int movieId);
}
