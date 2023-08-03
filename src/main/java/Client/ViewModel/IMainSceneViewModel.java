package Client.ViewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface IMainSceneViewModel {
    StringProperty logInTextStringProperty();
    BooleanProperty administratorProperty();
    BooleanProperty loggedInProperty();

    /**
     * Checks if the user is logged in
     * @return Login status
     */
    boolean isLoggedIn();
    void logOut();
}
