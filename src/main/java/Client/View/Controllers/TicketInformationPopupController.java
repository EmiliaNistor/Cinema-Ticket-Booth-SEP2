package Client.View.Controllers;

import Client.Core.ViewHandler;
import Client.ViewModel.IViewTicketPopupViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TicketInformationPopupController
{
    private IViewTicketPopupViewModel viewModel;
    private ViewHandler viewHandler; // Add the ViewHandler variable here

    @FXML
    private TextField inputField;

    public void init(IViewTicketPopupViewModel viewModel,ViewHandler viewHandler)
    {
        this.viewModel = viewModel;
        this.viewHandler= viewHandler;
    }


    @FXML
    private void cancel(ActionEvent actionEvent)
    {
        // Close ticket info popup controller window
        Stage stage = (Stage) inputField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void continueAction(ActionEvent actionEvent)
    {
        try {
            String input = inputField.getCharacters().toString();
            int ticketID = Integer.parseInt(input);

            if (viewModel.openTicketInfo(ticketID)) {
                // Close ticket info popup controller window
                Stage stage = (Stage) inputField.getScene().getWindow();
                stage.close();
                return;
            }

            showError("Failed to get ticket information! Please double check your input.");
        } catch (NumberFormatException ignored) {}

        showError("Failed to get ticket information! Please double check your input.");
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

