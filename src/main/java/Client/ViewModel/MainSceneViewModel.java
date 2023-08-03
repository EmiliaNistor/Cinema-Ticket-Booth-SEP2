package Client.ViewModel;

import Client.Core.PropertyChangeSubject;
import Client.Core.ViewModelFactory;
import Client.Model.IAccountModel;
import Shared.Model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class MainSceneViewModel implements IMainSceneViewModel {
    private final IAccountModel accountModel;
    private final BooleanProperty loggedInProperty;
    private final BooleanProperty administratorProperty;
    private final StringProperty logInTextProperty;
    private final ViewModelFactory viewModelFactory;

    public MainSceneViewModel(ViewModelFactory viewModelFactory, IAccountModel accountModel)
    {
        this.viewModelFactory = viewModelFactory;
        this.accountModel = accountModel;

        loggedInProperty = new SimpleBooleanProperty(false);
        administratorProperty = new SimpleBooleanProperty(false);
        logInTextProperty = new SimpleStringProperty("Log In");

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
            logInTextProperty.setValue("Log In");
            return;
        }

        User newAccount = (User) event.getNewValue();
        loggedInProperty.setValue(true);
        administratorProperty.setValue(newAccount.getAdministrator());
        logInTextProperty.setValue("Log Out");
    }

    @Override
    public StringProperty logInTextStringProperty() {
        return logInTextProperty;
    }

    @Override
    public BooleanProperty administratorProperty() {
        return administratorProperty;
    }

    @Override
    public BooleanProperty loggedInProperty() {
        return loggedInProperty;
    }

    /**
     * Checks if the user is logged in
     * @return Login status
     */
    @Override
    public boolean isLoggedIn() {
        return loggedInProperty.getValue();
    }

    @Override
    public void logOut() {
        accountModel.logOut();
    }
}
