package Client.ViewModel;

import Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

public class movieListViewModel {
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    public void initialize() {
        List<Movie> movieData = Arrays.asList(
                new Movie("Movie 1", "Genre 1", 120, 1),
                new Movie("Movie 2", "Genre 2", 150, 2),
                new Movie("Movie 3", "Genre 3", 100, 1)
        );

        movieList.addAll(movieData);
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }
}

