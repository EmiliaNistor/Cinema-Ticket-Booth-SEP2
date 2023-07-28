package Client.ViewModel;

import Shared.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MovieListViewModel implements IMovieListViewModel {
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    public void initialize() {
        List<Movie> movieData = Arrays.asList(
                new Movie(1, "Genre 1", new Date(), "horror", 1),
                new Movie(2, "Genre 2", new Date(), "funi", 2),
                new Movie(3, "Genre 3", new Date(),"action", 1)
        );

        movieList.addAll(movieData);
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }


}

