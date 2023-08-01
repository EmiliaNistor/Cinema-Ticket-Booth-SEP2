package Client.ViewModel;

import Client.Core.ViewModelFactory;
import Client.Model.IMovieListModel;
import Client.Model.IScreenModel;
import Client.Model.ITicketModel;
import Shared.Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;
import java.util.ArrayList;

public class PurchaseTicketPopUpViewModel implements IPurchaseTicketPopUpViewModel {
    private Movie movie;
    private final IMovieListModel movieModel;
    private final IScreenModel screenModel;
    private final ITicketModel ticketModel;

    private final StringProperty movieName;
    private final StringProperty movieScreen;
    private final StringProperty movieLength;
    private final StringProperty movieDate;
    private final StringProperty ticketPrice;
    private final ObservableList<LocalTime> movieStartTimes;
    private final StringProperty movieEndTime;
    private final ObservableList<Seat> seatOptions;
    private final ObservableList<Menu> menuOptions;

    public PurchaseTicketPopUpViewModel(IMovieListModel movieModel, IScreenModel screenModel, ITicketModel ticketModel) {
        this.movieModel = movieModel;
        this.screenModel = screenModel;
        this.ticketModel = ticketModel;

        movieName = new SimpleStringProperty();
        movieScreen = new SimpleStringProperty();
        movieLength = new SimpleStringProperty();
        movieDate = new SimpleStringProperty();
        ticketPrice = new SimpleStringProperty();
        movieStartTimes = FXCollections.observableArrayList();
        movieEndTime = new SimpleStringProperty();
        seatOptions = FXCollections.observableArrayList();
        menuOptions = FXCollections.observableArrayList();
    }

    /**
     * Sets the currently active movie for the ticket purchase
     * @param movie The movie to use
     */
    public void setMovie(Movie movie) {
        this.movie = movie;

        // updating properties
        movieName.setValue(movie.getName());
        movieLength.setValue(""+movie.getLength());
        movieDate.setValue(movie.getDate().toString());
        movieEndTime.setValue(movie.getEndTime().toString());
    }

    @Override
    public StringProperty getMovieNameProperty() {
        return movieName;
    }

    @Override
    public StringProperty getMovieScreenProperty() {
        return movieScreen;
    }

    @Override
    public StringProperty getMovieLengthProperty() {
        return movieLength;
    }

    @Override
    public StringProperty getMovieDateProperty() {
        return movieDate;
    }

    @Override
    public StringProperty getTicketPriceProperty() {
        return ticketPrice;
    }

    @Override
    public StringProperty getMovieEndTimeProperty() {
        return movieEndTime;
    }

    @Override
    public ObservableList<LocalTime> getMovieStartTimes() {
        return movieStartTimes;
    }

    @Override
    public ObservableList<Seat> getTicketSeatOptions() {
        return seatOptions;
    }

    @Override
    public ObservableList<Menu> getTicketMenuOptions() {
        return menuOptions;
    }

    /**
     * Updates the information about the movie from selected settings
     * @param movieStartTime The chosen movie start time, null if not changed
     * @param seat           The chosen seat for the ticket, null if not changed
     * @param menu           The chosen menu for the ticket, null if not changed
     */
    @Override
    public void updateTicketInfo(LocalTime movieStartTime, Seat seat, Menu menu) {
        if (movieStartTime != null) {
            // movie start time was changed, refreshing available seats and info
            ArrayList<Movie> sameDate = movieModel.getSameMoviesByDate(movie, movie.getDate());
            for (Movie m: sameDate) {
                if (m.getStartTime().equals(movieStartTime)) {
                    movie = m;
                    Screen matchingScreen = screenModel.getScreenByMovie(m);
                    if (matchingScreen != null) {
                        seatOptions.setAll(matchingScreen.getSeats());
                    } else {
                        seatOptions.clear();
                    }

                    return;
                }
            }

            return;
        }

        if (seat != null) {
            // nothing to change for now?
        }

        if (menu != null) {
            // nothing either?
        }
    }

    /**
     * Handle the purchase of a ticket
     * @param movieStartTime The chosen movie start time
     * @param seat The chosen seat for the ticket
     * @param menu The chosen menu for the ticket
     */
    @Override
    public void purchaseTicket(LocalTime movieStartTime, Seat seat, Menu menu) {
        ticketModel.purchaseTicket(
                new Ticket(-1, seat, movie, screenModel.getScreenByMovie(movie), menu)
        );

    }
}


