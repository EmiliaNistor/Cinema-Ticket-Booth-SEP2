package Client.ViewModel;

import Client.Model.IAccountModel;

public class LogInViewModel implements ILogInViewModel {
    private final IAccountModel accountModel;

    public LogInViewModel(IAccountModel accountModel) {
        this.accountModel = accountModel;
    }

    @Override
    public boolean logIn(String username, String password) {
        if (accountModel.logIn(username,password)) {
            return true;
        }
        return false;
    }
}
