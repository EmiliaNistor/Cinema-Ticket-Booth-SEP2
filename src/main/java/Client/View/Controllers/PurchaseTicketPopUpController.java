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

        // setting the listeners
        // name
        viewModel.getMovieNameProperty().addListener(
                (observable, oldValue, newValue) -> movieName.setText(newValue)
        );
        // date
        viewModel.getMovieDateProperty().addListener(
                (observable, oldValue, newValue) -> movieDate.setText(newValue)
        );
        // length
        viewModel.getMovieLengthProperty().addListener(
                (observable, oldValue, newValue) -> movieLength.setText(newValue)
        );
        // start times
        movieStartTimes.setItems(viewModel.getMovieStartTimes());
        // end time
        viewModel.getMovieEndTimeProperty().addListener(
                (observable, oldValue, newValue) -> movieEndTime.setText(newValue)
        );
        // price
        viewModel.getTicketPriceProperty().addListener(
                (observable, oldValue, newValue) -> ticketPrice.setText(newValue)
        );
        // seat choices
        seatSelection.setItems(viewModel.getTicketSeatOptions());
        // menu choices
        menuSelection.setItems(viewModel.getTicketMenuOptions());
    }

    @FXML
    private void cancel(ActionEvent action) {
        Stage stage = (Stage) movieName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void menuSelected(ActionEvent action) {
        viewModel.updateTicketInfo(
                null, null, menuSelection.getValue()
        );
    }

    @FXML
    private void seatSelected(ActionEvent action) {
        viewModel.updateTicketInfo(
                null, seatSelection.getValue(), null
        );
    }

    @FXML
    private void startTimeSelected(ActionEvent action) {
        viewModel.updateTicketInfo(
                movieStartTimes.getValue(), null, null
        );
    }

    @FXML
    private void pay(ActionEvent action) {
        viewModel.purchaseTicket(
                movieStartTimes.getValue(), seatSelection.getValue(), menuSelection.getValue()
        );
        Stage stage = (Stage) movieName.getScene().getWindow();
        stage.close();
    }
}
