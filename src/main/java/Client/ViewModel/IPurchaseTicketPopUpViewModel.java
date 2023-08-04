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
     * @param movieStartTime The chosen movie start time
     */
    void updateMovieStart(LocalTime movieStartTime);
    /**
     * Updates the information about the selected menu
     * @param menu The chosen menu item
     */
    void updateMenu(Menu menu);

    /**
     * Handle the purchase of a ticket
     * @param movieStartTime The chosen movie start time
     * @param seat The chosen seat for the ticket
     * @param menu The chosen menu for the ticket
     * @return True if successful
     */
    boolean purchaseTicket(LocalTime movieStartTime, Seat seat, Menu menu);
}
