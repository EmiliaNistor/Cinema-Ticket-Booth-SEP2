package Client.ViewModel;

public interface ILogInViewModel {
    /**
     * Attempt to log into an account
     * @param username Account's username
     * @param password Account's password
     * @return True if successful
     */
    boolean logIn(String username, String password);
}
