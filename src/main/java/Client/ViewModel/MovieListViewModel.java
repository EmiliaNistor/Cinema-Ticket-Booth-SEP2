package Client.ViewModel;

import Shared.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieListViewModel implements IMovieListViewModel {
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    public void initialize() {
        List<Movie> movieData = Arrays.asList(
                new Movie(1, "Genre 1", LocalDate.of(2023, 7, 28), "horror", 1),
                new Movie(2, "Genre 2", LocalDate.of(2023, 7, 29), "funi", 2),
                new Movie(3, "Genre 3", LocalDate.of(2023, 7, 30),"action", 1)
        );

        movieList.addAll(movieData);
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }


}

