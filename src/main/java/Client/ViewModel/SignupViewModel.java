package Client.ViewModel;

import Client.Model.IAccountModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignupViewModel implements ISignupViewModel {
    private final IAccountModel accountModel; // Instance of the AccountModel

    public SignupViewModel(IAccountModel accountModel) {
        this.accountModel = accountModel;
    }

    @Override
    public void signup(String username, String password){

        // Perform validation on the input (e.g., check if fields are not empty)
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please fill in both username and password.");
            return;
        }

        // Call the signup method from the SignupModel
        boolean signupSuccessful = accountModel.signup(username, password);

        if (signupSuccessful) {
            showInfo("Signup successful! Welcome, " + username + "!");
        } else {
            showError("Signup failed. Please try again.");
        }
    }

    // Helper methods to display alerts for showing messages
    private void showError(String message) {
        showAlert(AlertType.ERROR, "Error", message);
    }

    private void showInfo(String message) {
        showAlert(AlertType.INFORMATION, "Information", message);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
