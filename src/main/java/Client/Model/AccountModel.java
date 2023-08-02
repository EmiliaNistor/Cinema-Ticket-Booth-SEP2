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

    /**
     * Attempt to create a new user account on the server.
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @return True if sign up has been successful
     */
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
        } catch (RemoteException e)
        {
            System.out.println("Error occurred during signup process.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Attempt to log in to a user's account
     * @param username The username of the account
     * @param password The password of the account
     * @return True if logging in has been successful
     */
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
        } catch (RemoteException e)
        {
            System.out.println("Error occurred during log in process.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Log out of currently logged in account
     */
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
