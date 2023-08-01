package Client.ViewModel;

import Client.Core.ViewModelFactory;
import Client.Model.IMovieListModel;
import Shared.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieListViewModel implements IMovieListViewModel
{
    private IMovieListModel movieListModel;
    private ObservableList<Movie> movieList;
    private ViewModelFactory viewModelFactory;

    public MovieListViewModel(ViewModelFactory viewModelFactory, IMovieListModel movieListModel)
    {
        this.viewModelFactory = viewModelFactory;
        this.movieListModel = movieListModel;
        this.movieList = FXCollections.observableArrayList(
                movieListModel.getAllMovies()
        );

    }

    @Override
    public ObservableList<Movie> getMovieList()
    {
        //return movieListModel.getAllMovies();
        // Updating the movie list array

        return movieList;
    }

    /**
     * Changes the purchase ticket popup information to a new movie
     * @param movie The movie to purchase a ticket for
     */
    @Override
    public void setPopUpMovie(Movie movie) {
        viewModelFactory.updatePurchaseTicketMovie(movie);
    }
}

