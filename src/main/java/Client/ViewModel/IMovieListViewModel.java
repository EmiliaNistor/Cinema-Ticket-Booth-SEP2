package Client.ViewModel;

import Shared.Model.Movie;
import javafx.collections.ObservableList;

public interface IMovieListViewModel {
    ObservableList<Movie> getMovieList();
}
