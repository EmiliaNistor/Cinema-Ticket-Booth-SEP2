package Client.Model;

import Client.Core.PropertyChangeSubject;
import Shared.Model.Screen;
import Shared.Network.IRMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ScreenModel implements IScreenModel, PropertyChangeSubject {
    private final HashMap<Integer,Screen> screens;
    private final IRMIServer rmiServer;
    private final PropertyChangeSupport propertyChangeSupport;

    public ScreenModel(IRMIServer rmiServer) {
        this.rmiServer = rmiServer;
        this.propertyChangeSupport = new PropertyChangeSupport(this);

        screens = new HashMap<>();
    }

    /**
     * Gets the screen provided by it's id
     * @param screenId The screen id
     * @return Screen, null if not found
     */
    @Override
    public Screen getScreenById(int screenId) {
        if (screens.containsKey(screenId)) {
            return screens.get(screenId);
        }

        // Attempting to get screen from server
        try {
            Screen receivedScreen = rmiServer.getScreenById(screenId);
            if (receivedScreen != null) {
                // populating screens list
                ArrayList<Screen> screenAR = new ArrayList<>(screens.values());
                screenAR.add(receivedScreen);

                propertyChangeSupport.firePropertyChange(
                        "ScreensChange", new ArrayList<>(screens.values()), screenAR
                );
                screens.put(receivedScreen.getScreenId(), receivedScreen);
                return receivedScreen;
            }
        } catch (RemoteException e) {
            System.out.println("Error occurred during screen fetching process.");
            e.printStackTrace();
        }

        // screen not found or an error
        return null;
    }

    /**
     * Refreshes information about screens from the server
     */
    @Override
    public void refreshScreens() {
        try {
            ArrayList<Screen> receivedScreens = rmiServer.getAllScreens();
            if (receivedScreens != null) {
                // successful fetch
                Collection<Screen> oldScreenList = screens.values();

                // populating screens list
                screens.clear();
                ArrayList<Screen> screenAR = new ArrayList<>();
                for (Screen s: receivedScreens) {
                    screens.put(s.getScreenId(), s);
                    screenAR.add(s);
                }

                propertyChangeSupport.firePropertyChange(
                        "ScreensChange", oldScreenList, screenAR
                );
            }
        } catch (RemoteException e) {
            System.out.println("Error occurred during screen fetching process.");
            e.printStackTrace();
        }
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
