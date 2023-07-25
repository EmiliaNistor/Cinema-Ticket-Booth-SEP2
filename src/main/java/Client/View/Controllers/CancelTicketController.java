package Client.View.Controllers;

import Client.ViewModel.ICancelTicketViewModel;
import Shared.Model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CancelTicketController {
    @FXML
    private Label titleText;
    @FXML
    private Label infoText;

    /**
     * Controller's view model
     */
    private ICancelTicketViewModel viewModel;
    /**
     * The ticket which is about to be cancelled
     */
    private Ticket ticket;
    /**
     * Cancel ticket controller stage (window)
     */
    private Stage window;

    /**
     * Ticket's cancellation process has been cancelled
     * @param actionEvent Click's action event
     */
    @FXML
    private void cancel(ActionEvent actionEvent) {
        // Close cancel ticket controller window
        window.close();
    }

    /**
     * Ticket's cancellation has been confirmed
     * @param actionEvent Click's action event
     */
    @FXML
    private void confirm(ActionEvent actionEvent) {
        viewModel.confirmTicketCancellation(ticket);
        window.close();
    }

    /**
     * Sets the base information about the controller
     * @param viewModel The controller's view model
     * @param ticket The ticket which the controller is responsible for
     * @param stage The controller's stage (window)
     */
    public void init(ICancelTicketViewModel viewModel, Ticket ticket, Stage stage) {
        this.viewModel = viewModel;
        this.ticket = ticket;
        this.window = stage;
        titleText.setText(
                String.format("Cancel ticket [%d]", ticket.getId())
        );
        infoText.setText(
                String.format("Are you sure you want to cancel ticket for movie [%s]?",
                        ticket.getMovie().getName())
        );
    }
}
