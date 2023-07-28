package Client.ViewModel;

import Client.Model.IMovieListModel;
import Client.Model.MovieListModel;
import Shared.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieListViewModel implements IMovieListViewModel
{
    private IMovieListModel movieListModel;
    private ObservableList<Movie> movieList;



    public MovieListViewModel(IMovieListModel movieListModel)
    {
        this.movieListModel = movieListModel;
        this.movieList = FXCollections.observableArrayList();;

    }


    @Override
    public ObservableList<Movie> getMovieList()
    {
        //return movieListModel.getAllMovies();
        // Updating the movie list array
        movieList = FXCollections.observableArrayList(
                movieListModel.getAllMovies()
        );

        return movieList;
    }


}

