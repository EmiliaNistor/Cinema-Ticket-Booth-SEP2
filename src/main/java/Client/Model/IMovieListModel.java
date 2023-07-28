package Client.Model;

import Shared.Model.Movie;

import java.util.ArrayList;

public interface IMovieListModel
{
    /**
     * Gets all the movies
     * @return A list of movies
     */
    ArrayList<Movie> getAllMovies();
}
