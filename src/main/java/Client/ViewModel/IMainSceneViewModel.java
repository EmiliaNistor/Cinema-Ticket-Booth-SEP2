package Client.ViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface IMainSceneViewModel {
    StringProperty logInTextStringProperty();
    BooleanProperty administratorProperty();
    BooleanProperty loggedInProperty();
    BooleanProperty movieListVisible();
    BooleanProperty ticketInfoVisible();

    /**
     * Changes view to movie list
     */
    void changeToMovieList();
    /**
     * Changes view to ticket info
     */
    void changeToTicketInfo();
    boolean isLoggedIn();
    void logOut();
}
