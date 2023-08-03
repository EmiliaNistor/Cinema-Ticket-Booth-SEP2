package Client.Model;

public interface IAccountModel
{
    /**
     * Attempt to create a new user account on the server.
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @return True if signing up has been successful
     */
    boolean signup(String username, String password);

    /**
     * Attempt to log in to a user's account
     * @param username The username of the account
     * @param password The password of the account
     * @return True if logging in has been successful
     */
    boolean logIn(String username, String password);

    /**
     * Log out of currently logged in account
     */
    void logOut();
}
