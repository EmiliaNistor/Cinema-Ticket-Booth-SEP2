package Client.View.Controllers;

import Client.ViewModel.ISignupViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public ISignupViewModel signupViewModel;

    /**
     * Initializes the controller with the ISignupViewModel instance.
     * @param signupViewModel The ISignupViewModel to be used by the controller.
     */
    public void init(ISignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
    }

    @FXML
    private void handleSignup() {
        boolean success = signupViewModel.signup(usernameField.getText(), passwordField.getText());
        if (success) {
            showInfo("Sign up successful! Welcome, "+usernameField.getText());
        } else {
            showError("Sign up failed! Please try again.");
        }
    }

    // Helper methods to display alerts for showing messages
    private void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    private void showInfo(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Information", message);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
