package Client.Model;

import Shared.Model.Menu;

public interface IMenuModel {
    /**
     * Refreshes the list of all available menus
     */
    void getAllMenus();

    /**
     * Gets a menu's information from the server
     * @param menuId The menu's id to get
     * @return The menu's information
     */
    Menu getMenuById(int menuId);
}
