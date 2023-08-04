package Client.View.Controllers;

import Client.ViewModel.ILogInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {
    private ILogInViewModel viewModel;

    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;

    public void init(ILogInViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) usernameInput.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void logIn(ActionEvent event) {
        if (viewModel.logIn(usernameInput.getText(), passwordInput.getText())) {
            // logging in successful
            Stage stage = (Stage) usernameInput.getScene().getWindow();
            stage.close();
        } else {
            showError("Log in failed. Please try again.");
        }
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
