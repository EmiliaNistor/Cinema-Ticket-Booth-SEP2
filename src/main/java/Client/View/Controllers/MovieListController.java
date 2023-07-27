package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMovieListViewModel;

//import junit testing libraries for assertions and annotations for test methods (e.g. @Test)

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MovieListController {
    private ViewHandler viewHandler;
    private IMovieListViewModel viewModel;

    // Controller stuff
    @FXML
    private Button addMovieButton;
    @FXML
    private Button modifyMovieButton;
    @FXML
    private Button removeMovieButton;

    /**
     * Sets the base information about the controller
     * @param viewHandler View Handler to provide additional functionality
     * @param viewModel The controller's view model
     */
    public void init(ViewHandler viewHandler, IMovieListViewModel viewModel) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
    }

    @FXML
    private void addMovie(ActionEvent actionEvent) {

    }

    @FXML
    private void modifyMovie(ActionEvent actionEvent) {

    }

    @FXML
    private void removeMovie(ActionEvent actionEvent) {

    }

    @FXML
    private void purchaseTicket(ActionEvent actionEvent) {

    }
}
