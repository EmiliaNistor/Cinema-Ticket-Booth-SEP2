package Client.ViewModel;

import Client.Model.IMovieListModel;
import Client.Model.MovieListModel;
import Shared.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.List;

public class MovieListViewModel implements IMovieListViewModel
{
    private IMovieListModel movieListModel;
    private ObservableList<Movie> movieList;

    public MovieListViewModel(IMovieListModel movieListModel)
    {
        this.movieListModel = movieListModel;
        this.movieList = FXCollections.observableArrayList();;
    }

//    public void initialize()
//    {
//        List<Movie> movieData = Arrays.asList(
//                new Movie(1, "Genre 1", "horror", 1),
//                new Movie(2, "Genre 2", "funi", 2),
//                new Movie(3, "Genre 3", "action", 1)
//        );
//
//        movieList.addAll(movieData);
//    }

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

