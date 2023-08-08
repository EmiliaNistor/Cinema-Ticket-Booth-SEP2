package Client.Model;

import Client.Core.PropertyChangeSubject;
import Shared.Model.User;
import Shared.Network.IRMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class AccountModel implements IAccountModel, PropertyChangeSubject {
    private final IRMIServer serverRMI;
    private User loggedInUser;
    private final PropertyChangeSupport propertyChangeSupport;

    public AccountModel(IRMIServer serverRMI) {
        this.serverRMI = serverRMI;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public boolean signup(String username, String password)
    {
        try
        {
            // Send the signup data to the server for processing.
            User createdUser = serverRMI.signUp(username, password);
            if (createdUser == null) {
                return false;
            }

            propertyChangeSupport.firePropertyChange("AccountChange", loggedInUser, createdUser);
            loggedInUser = createdUser;
            return true;
        } catch (RemoteException e)
        {
            System.out.println("Error occurred during signup process.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean logIn(String username, String password) {
        try
        {
            // Send the log in data to the server for processing.
            User loggedInUser = serverRMI.logIn(username, password);
            if (loggedInUser == null) {
                return false;
            }

            propertyChangeSupport.firePropertyChange("AccountChange", this.loggedInUser, loggedInUser);
            this.loggedInUser = loggedInUser;
            return true;
        } catch (RemoteException e)
        {
            System.out.println("Error occurred during log in process.");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void logOut() {
        propertyChangeSupport.firePropertyChange("AccountChange", this.loggedInUser, null);
        loggedInUser = null;
    }

    // Property Change Subject implementations
    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
