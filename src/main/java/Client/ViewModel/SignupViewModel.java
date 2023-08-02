package Client.ViewModel;

import Client.Model.IAccountModel;

public class SignupViewModel implements ISignupViewModel {
    private final IAccountModel accountModel; // Instance of the AccountModel

    public SignupViewModel(IAccountModel accountModel) {
        this.accountModel = accountModel;
    }

    @Override
    public boolean signup(String username, String password){

        // Perform validation on the input (e.g., check if fields are not empty)
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Call the signup method from the SignupModel
        boolean signupSuccessful = accountModel.signup(username, password);

        if (signupSuccessful) {
            return true;
        } else {
            return false;
        }
    }
}
