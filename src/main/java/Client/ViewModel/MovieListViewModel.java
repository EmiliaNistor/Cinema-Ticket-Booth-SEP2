package Client.ViewModel;

import Client.Core.PropertyChangeSubject;
import Client.Core.ViewModelFactory;
import Client.Model.IAccountModel;
import Client.Model.IMovieListModel;
import Shared.Model.Movie;
import Shared.Model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

public class MovieListViewModel implements IMovieListViewModel
{
    private final IMovieListModel movieListModel;
    private final IAccountModel accountModel;
    private final ObservableList<Movie> movieList;
    private final BooleanProperty loggedInProperty;
    private final BooleanProperty administratorProperty;
    private final ViewModelFactory viewModelFactory;

    public MovieListViewModel(ViewModelFactory viewModelFactory, IMovieListModel movieListModel, IAccountModel accountModel)
    {
        this.viewModelFactory = viewModelFactory;
        this.movieListModel = movieListModel;
        this.accountModel = accountModel;
        this.movieList = FXCollections.observableArrayList();

        loggedInProperty = new SimpleBooleanProperty(false);
        administratorProperty = new SimpleBooleanProperty(false);

        ((PropertyChangeSubject) accountModel).addPropertyChangeListener(
                "AccountChange",
                (PropertyChangeEvent evt) -> this.updateButtonAccess(evt)
        );

        ((PropertyChangeSubject) movieListModel).addPropertyChangeListener(
                "MovieListChange",
                (PropertyChangeEvent evt) -> this.updateMovieList(evt)
        );
    }

    private void updateButtonAccess(PropertyChangeEvent event) {
        if (event.getNewValue() == null) {
            // logged out
            loggedInProperty.setValue(false);
            administratorProperty.setValue(false);
            return;
        }

        User newAccount = (User) event.getNewValue();
        loggedInProperty.setValue(true);
        administratorProperty.setValue(newAccount.getAdministrator());
    }

    private void updateMovieList(PropertyChangeEvent event) {
        if (event.getNewValue() == null) {
            movieList.clear();
            return;
        }

        ArrayList<Movie> movies = new ArrayList<>();
        movies.addAll((ArrayList<Movie>) event.getNewValue());

        movieList.setAll(movies);
    }

    @Override
    public ObservableList<Movie> getMovieList()
    {
        return movieList;
    }

    /**
     * Changes the purchase ticket popup information to a new movie
     * @param movie The movie to purchase a ticket for
     */
    @Override
    public void setPopUpMovie(Movie movie) {
        viewModelFactory.getPurchaseTicketPopUpViewModel().setMovie(movie);
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

