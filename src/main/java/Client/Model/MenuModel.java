package Client.Model;

import Client.Core.PropertyChangeSubject;
import Shared.Model.Menu;
import Shared.Network.IRMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MenuModel implements IMenuModel, PropertyChangeSubject {
    private final IRMIServer serverRMI;
    private final HashMap<Integer, Menu> menuList;
    private final PropertyChangeSupport propertyChangeSupport;

    public MenuModel(IRMIServer serverRMI) {
        this.serverRMI = serverRMI;
        this.menuList = new HashMap<>();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Refreshes the list of all available menus
     */
    @Override
    public void getAllMenus() {
        try {
            ArrayList<Menu> menus = serverRMI.getAllMenuItems();
            if (menus != null) {
                // successful fetch
                Collection<Menu> oldMenuList = menuList.values();

                // populating menu list
                menuList.clear();
                for (Menu m: menus) {
                    menuList.put(m.getMenuId(), m);
                }

                propertyChangeSupport.firePropertyChange(
                        "MenuListChange", oldMenuList, menuList.values()
                );
            }
        } catch (RemoteException e) {
            System.out.println("Error occurred during menu fetching process.");
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
