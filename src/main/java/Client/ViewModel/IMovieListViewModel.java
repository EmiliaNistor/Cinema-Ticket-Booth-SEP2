package Client.ViewModel;

import Shared.Model.Movie;
import javafx.collections.ObservableList;

public interface IMovieListViewModel {
    ObservableList<Movie> getMovieList();

    /**
     * Opens a new popup window to make a ticket purchase
     * @param movie The movie to purchase a ticket for
     */
    void setPopUpMovie(Movie movie);
}
