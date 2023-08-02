package Client.View.Controllers;

import Client.ViewModel.ISignupViewModel;
import javafx.fxml.FXML;
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
        signupViewModel.signup(usernameField.getText(), passwordField.getText());
    }
}
