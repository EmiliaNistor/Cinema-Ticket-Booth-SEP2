package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IMovieListViewModel;
import Client.ViewModel.IPurchaseTicketPopUpViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private ChoiceBox<String> movieStartTimes;
    @FXML
    private Label movieEndTime;
    @FXML
    private Label movieLength;
    @FXML
    private ComboBox<String> seatRowBox;
    @FXML
    private ComboBox<Integer> seatNumberBox;
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
    }
}
