package Client.Model;

import Shared.Model.Movie;
import Shared.Model.Screen;
import Shared.Network.IRMIServer;

import java.util.ArrayList;

public class ScreenModel implements IScreenModel {
    /**
     * Model to get information about the movies
     */
    private IMovieListModel movieListModel;
    private ArrayList<Screen> screens;
    private IRMIServer rmiServer;

    public ScreenModel(IRMIServer rmiServer, IMovieListModel movieListModel) {
        this.rmiServer = rmiServer;
        this.movieListModel = movieListModel;

        screens = new ArrayList<>();
    }

    /**
     * Returns the screen which the movie is shown on
     * @param movie The movie to look for
     * @return The screen it's shown on, null if not found
     */
    @Override
    public Screen getScreenByMovie(Movie movie) {
        ArrayList<Movie> movies = movieListModel.getAllMovies();
        for (Screen s: screens) {
            for (Movie m: s.getMovies()) {
                if (m.getMovieId() == movie.getMovieId()) {
                    return s;
                }
            }
        }

        return null;
    }

    /**
     * Gets the screen provided by it's id
     * @param screenId The screen id
     * @return Screen, null if not found
     */
    @Override
    public Screen getScreenById(int screenId) {
        for (Screen s: screens) {
            if (s.getScreenId() == screenId) {
                return s;
            }
        }

        return null;
    }
}
