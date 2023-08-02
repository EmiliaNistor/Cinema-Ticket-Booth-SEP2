package Client.ViewModel;

import Client.Core.PropertyChangeSubject;
import Client.Core.ViewModelFactory;
import Client.Model.IAccountModel;
import Client.Model.IMovieListModel;
import Shared.Model.Movie;
import Shared.Model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MovieListViewModel implements IMovieListViewModel
{
    private final IMovieListModel movieListModel;
    private final IAccountModel accountModel;
    private ObservableList<Movie> movieList;
    private BooleanProperty loggedInProperty;
    private BooleanProperty administratorProperty;
    private final ViewModelFactory viewModelFactory;

    public MovieListViewModel(ViewModelFactory viewModelFactory, IMovieListModel movieListModel, IAccountModel accountModel)
    {
        this.viewModelFactory = viewModelFactory;
        this.movieListModel = movieListModel;
        this.accountModel = accountModel;
        this.movieList = FXCollections.observableArrayList(
                movieListModel.getAllMovies()
        );

        loggedInProperty = new SimpleBooleanProperty(false);
        administratorProperty = new SimpleBooleanProperty(false);

        ((PropertyChangeSubject) accountModel).addPropertyChangeListener(
                "AccountChange",
                (PropertyChangeEvent evt) -> this.updateButtonAccess(evt)
        );
    }

    private void updateButtonAccess(PropertyChangeEvent event) {
        if (event.getNewValue() == null) {
            // logged out
            loggedInProperty.setValue(false);
            administratorProperty.setValue(false);
        }

        User newAccount = (User) event.getNewValue();
        loggedInProperty.setValue(true);
        administratorProperty.setValue(newAccount.getAdministrator());
    }

    @Override
    public ObservableList<Movie> getMovieList()
    {
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

    @Override
    public BooleanProperty administratorProperty() {
        return administratorProperty;
    }

    @Override
    public BooleanProperty loggedInProperty() {
        return loggedInProperty;
    }
}

