package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMovieListViewModel;
import Client.ViewModel.IPurchaseTicketPopUpViewModel;
import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalTime;

public class PurchaseTicketPopUpController {
    // Internal controller stuff
    /**
     * Controller's access to view handler
     */
    private ViewHandler viewHandler;
    /**
     * Controller's view model
     */
    private IPurchaseTicketPopUpViewModel viewModel;

    // FXML stuff
    @FXML
    private Label movieName;
    @FXML
    private Label movieDate;
    @FXML
    private ChoiceBox<LocalTime> movieStartTimes;
    @FXML
    private Label movieEndTime;
    @FXML
    private Label movieLength;
    @FXML
    private ComboBox<Seat> seatSelection;
    @FXML
    private ComboBox<Menu> menuSelection;
    @FXML
    private Label ticketPrice;

    /**
     * Sets the base information about the controller
     * @param viewHandler View Handler to provide additional functionality
     * @param viewModel   The controller's view model
     */
    public void init(ViewHandler viewHandler, IPurchaseTicketPopUpViewModel viewModel)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;

        // binding values
        movieName.textProperty().bind(viewModel.getMovieNameProperty());
        movieDate.textProperty().bind(viewModel.getMovieDateProperty());
        movieLength.textProperty().bind(viewModel.getMovieLengthProperty());
        movieStartTimes.setItems(viewModel.getMovieStartTimes());
        movieEndTime.textProperty().bind(viewModel.getMovieEndTimeProperty());
        ticketPrice.textProperty().bind(viewModel.getTicketPriceProperty());
        seatSelection.setItems(viewModel.getTicketSeatOptions());
        menuSelection.setItems(viewModel.getTicketMenuOptions());
    }

    @FXML
    private void cancel(ActionEvent action) {
        Stage stage = (Stage) movieName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void menuSelected(ActionEvent action) {
        viewModel.updateMenu(menuSelection.getValue());
    }

    @FXML
    private void seatSelected(ActionEvent action) {;
    }

    @FXML
    private void startTimeSelected(ActionEvent action) {
        viewModel.updateMovieStart(movieStartTimes.getValue());
    }

    @FXML
    private void pay(ActionEvent action) {
        if (movieStartTimes.getValue() == null || seatSelection.getValue() == null) {
            showError("Ticket purchase failed!");
            return;
        }

        viewModel.purchaseTicket(
                movieStartTimes.getValue(), seatSelection.getValue(), menuSelection.getValue()
        );
        Stage stage = (Stage) movieName.getScene().getWindow();
        stage.close();
    }

    // Helper methods to display alerts for showing messages
    private void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
