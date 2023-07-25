package Client.View.Controllers;

import Client.ViewModel.ICancelTicketViewModel;
import Client.ViewModel.IViewTicketPopupViewModel;
import Shared.Model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketInformationPopupController {
    /**
     * Controller's view model
     */
    private IViewTicketPopupViewModel viewModel;
    /**
     * Ticket info popup controller stage (window)
     */
    private Stage window;
    /**
     * Ticket's ID input field
     */
    @FXML
    private TextField inputField;
    /**
     * Button responsible for opening information about the ticket
     */
    @FXML
    private Button continueButton;

    //will be used for later
    //controller.init(this, viewModelFactory.makeMainViewModel());

    /**
     * Sets the base information about the controller
     * @param viewModel The controller's view model
     * @param stage The controller's stage (window)
     */
    public void init(IViewTicketPopupViewModel viewModel, Stage stage) {
        this.viewModel = viewModel;
        this.window = stage;
    }

    /**
     * Validates the ticket ID input field value
     */
    private void validateInput() {
        String input = inputField.getCharacters().toString();

        try {
            int ticketID = Integer.parseInt(input);
            if (ticketID < 1) {
                continueButton.setDisable(true);
                return;
            }

            // all checks passed
            continueButton.setDisable(false);
        } catch (NumberFormatException e) { // couldn't parse number
            continueButton.setDisable(true);
        }
    }

    /**
     * Opening ticket's information UI has been cancelled
     * @param actionEvent Click's action event
     */
    @FXML
    private void cancel(ActionEvent actionEvent) {
        // Close ticket info popup controller window
        window.close();
    }

    /**
     * Opening ticket's information UI
     * @param actionEvent Click's action event
     */
    @FXML
    private void continueAction(ActionEvent actionEvent) {
        validateInput();

        if (continueButton.isDisabled()) {
            return;
        }
        // Close ticket info popup controller window
        window.close();

        // open ticket info window
        String input = inputField.getCharacters().toString();
        viewModel.openTicketInfoView(
                Integer.parseInt(input)
        );
    }

    /**
     * Input text has changed
     * @param actionEvent Input's action event
     */
    @FXML
    private void inputChanged(ActionEvent actionEvent) {
        validateInput();
    }
}
