package Client.ViewModel;

import Client.Model.ISignupModel;
import Client.Model.SignupModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignupViewModel implements ISignupViewModel {
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private ISignupModel signupModel; // Instance of the SignupModel

    public SignupViewModel(ISignupModel signupModel) {
        this.signupModel = signupModel;
        usernameProperty = new SimpleStringProperty();
        passwordProperty = new SimpleStringProperty();
    }

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    @Override
    public void signup(String username, String password){

        // Perform validation on the input (e.g., check if fields are not empty)
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please fill in both username and password.");
            return;
        }

        // Call the signup method from the SignupModel
        boolean signupSuccessful = signupModel.signup(username, password);

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
