package Client.Model;

import Shared.Model.Movie;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IMovieListModel
{
    /**
     * Get all the movies
     * @return A list of movies
     */
    public ObservableList<Movie> getAllMovies();

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
}
