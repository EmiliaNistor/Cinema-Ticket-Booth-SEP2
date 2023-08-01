package Client.Model;

import Shared.Model.Movie;
import Shared.Model.Screen;
import Shared.Model.Seat;

import java.util.ArrayList;

/**
 * Manages and holds information about screens, including their seats and movies
 */
public interface IScreenModel {
    /**
     * Returns the screen which the movie is shown on
     * @param movie The movie to look for
     * @return The screen it's shown on, null if not found
     */
    Screen getScreenByMovie(Movie movie);

    /**
     * Gets the screen provided by it's id
     * @param screenId The screen id
     * @return Screen, null if not found
     */
    Screen getScreenById(int screenId);
}
