package Client.ViewModel;

import Shared.Model.Menu;
import Shared.Model.Seat;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.time.LocalTime;

public interface IPurchaseTicketPopUpViewModel {
    StringProperty getMovieNameProperty();
    StringProperty getMovieScreenProperty();
    StringProperty getMovieLengthProperty();
    StringProperty getMovieDateProperty();
    StringProperty getTicketPriceProperty();
    StringProperty getMovieEndTimeProperty();
    ObservableList<LocalTime> getMovieStartTimes();
    ObservableList<Seat> getTicketSeatOptions();
    ObservableList<Menu> getTicketMenuOptions();


    /**
     * Updates the information about the movie from selected settings
     * @param movieStartTime The chosen movie start time, null if not changed
     * @param seat           The chosen seat for the ticket, null if not changed
     * @param menu           The chosen menu for the ticket, null if not changed
     */
    void updateTicketInfo(LocalTime movieStartTime, Seat seat, Menu menu);

    /**
     * Handle the purchase of a ticket
     * @param movieStartTime The chosen movie start time
     * @param seat The chosen seat for the ticket
     * @param menu The chosen menu for the ticket
     */
    void purchaseTicket(LocalTime movieStartTime, Seat seat, Menu menu);
}
