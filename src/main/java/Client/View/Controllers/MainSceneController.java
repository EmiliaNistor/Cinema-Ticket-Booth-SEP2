package Client.View.Controllers;

import Client.Core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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

    // Controller related stuff
    /**
     * Main scene controller stage (window)
     */
    private ViewHandler viewHandler;
    /**
     * Controller's view model
     */
    //private IMainSceneViewModel viewModel;

    /**
     * Sets the base information about the controller
     * @param viewHandler View Handler to manage the program's scenes
     */
    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

        // setting default scene to movies
        viewMovies(null);
    }

    @FXML
    private void logIn(ActionEvent actionEvent) {

    }

    @FXML
    private void viewMovies(ActionEvent actionEvent) {
        // setting movie button to blue color
        moviesButton.setDefaultButton(true);
        /*selectedSubscene.setRoot(
                viewHandler.getMovieListScene()
        );*/
        var movieList = viewHandler.getMovieList();
        selectedSubsceneParent.getChildren().setAll(movieList);
        // auto resizing
        //movieList.prefWidthProperty().bind(mainContent.widthProperty());
        //movieList.prefHeightProperty().bind(mainContent.heightProperty());

        // setting other buttons to default color
        ticketInfoButton.setDefaultButton(false);
        editMoviesButton.setDefaultButton(false);
        menusButton.setDefaultButton(false);
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
