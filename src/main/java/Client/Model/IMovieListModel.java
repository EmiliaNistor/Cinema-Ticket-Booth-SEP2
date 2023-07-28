package Client.Model;

import Shared.Model.Movie;
import javafx.collections.ObservableList;

import java.util.List;

public interface IMovieListModel
{  public ObservableList<Movie> getAllMovies();
}
