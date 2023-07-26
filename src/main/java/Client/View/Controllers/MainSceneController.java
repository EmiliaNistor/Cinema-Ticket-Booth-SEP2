package Client.View.Controllers;

import Client.ViewModel.IMainSceneViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSceneController {
    // Sidebar buttons
    /**
     * Used to log in/out as administrator for extra access
     */
    @FXML
    private Button logInButton;
    /**
     * View a list of movies and purchase tickets for them
     */
    @FXML
    private Button moviesButton;
    /**
     * See information about a ticket
     */
    @FXML
    private Button ticketInfoButton;
    /**
     * Add or modify information about a movie
     */
    @FXML
    private Button editMoviesButton;
    /**
     * Modify information about a menu
     */
    @FXML
    private Button menusButton;

    // Subscene stuff
    /**
     * Parent that holds the selected subscene
     */
    @FXML
    private AnchorPane selectedSubsceneParent;
    /**
     * Subscene to display
     */
    @FXML
    private SubScene selectedSubscene;

    // Controller related stuff
    /**
     * Main scene controller stage (window)
     */
    private Stage window;
    /**
     * Controller's view model
     */
    private IMainSceneViewModel viewModel;

    /**
     * Sets the base information about the controller
     * @param viewModel The controller's view model
     * @param stage The controller's stage (window)
     */
    public void init(IMainSceneViewModel viewModel, Stage stage) {
        this.window = stage;
    }

    @FXML
    private void logIn(ActionEvent actionEvent) {

    }

    @FXML
    private void viewMovies(ActionEvent actionEvent) {

    }

    @FXML
    private void viewTicketInfo(ActionEvent actionEvent) {

    }

    @FXML
    private void editMovies(ActionEvent actionEvent) {

    }

    @FXML
    private void manageMenus(ActionEvent actionEvent) {

    }
}
