package Client.Model;

import Shared.Model.Screen;

/**
 * Manages and holds information about screens, including their seats and movies
 */
public interface IScreenModel {

    /**
     * Gets the screen provided by it's id
     * @param screenId The screen id
     * @return Screen, null if not found
     */
    Screen getScreenById(int screenId);

    /**
     * Refreshes information about screens from the server
     */
    void refreshScreens();
}
