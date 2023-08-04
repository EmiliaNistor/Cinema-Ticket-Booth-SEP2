package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMainSceneViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    private IMainSceneViewModel viewModel;

    /**
     * Sets the base information about the controller
     * @param viewHandler View Handler to manage the program's scenes
     */
    public void init(ViewHandler viewHandler, IMainSceneViewModel viewModel, Parent movieList, Parent ticketInfo) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        logInButton.textProperty().bind(viewModel.logInTextStringProperty());
        ticketInfoButton.visibleProperty().bind(viewModel.loggedInProperty());
        menusButton.visibleProperty().bind(viewModel.administratorProperty());

        // setting views
        insertView(movieList);
        insertView(ticketInfo);
        movieList.visibleProperty().bind(viewModel.movieListVisible());
        ticketInfo.visibleProperty().bind(viewModel.ticketInfoVisible());

        // setting default scene to movies
        viewMovies(null);
    }

    private void insertView(Parent view) {
        selectedSubsceneParent.getChildren().add(view);
        // auto resizing
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
    }

    @FXML
    private void logIn(ActionEvent actionEvent) {
        if (viewModel.isLoggedIn()) {
            // logging out
            viewModel.logOut();
            return;
        }

        // opening log in window
        viewHandler.openLogIn();
    }

    @FXML
    private void viewMovies(ActionEvent actionEvent) {
        // setting button "colors"
        moviesButton.setDefaultButton(true);
        ticketInfoButton.setDefaultButton(false);
        menusButton.setDefaultButton(false);
    }

    @FXML
    private void viewTicketInfo(ActionEvent actionEvent) {
        Stage window = viewHandler.createViewTicketPopup();
        window.show();
    }

    @FXML
    private void manageMenus(ActionEvent actionEvent) {

    }

    @FXML
    private void signUp(ActionEvent actionEvent)
    {
        viewHandler.createSignUpWindow();
    }
}
