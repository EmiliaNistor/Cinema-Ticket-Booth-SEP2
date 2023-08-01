package Client.Model;

import Shared.Network.IRMIServer;

import java.rmi.RemoteException;

public class SignupModel implements ISignupModel
{
    private final IRMIServer serverRMI;

    public SignupModel(IRMIServer serverRMI)
    {
        this.serverRMI = serverRMI;
    }

    /**
     * Attempt to create a new user account on the server.
     *
     * @param username The username for the new account.
     * @param password The password for the new account.
     * @return true if the signup process was successful, false otherwise.
     */
    @Override
    public boolean signup(String username, String password)
    {
        try
        {
            // Perform any client-side validation here if needed.
            // For example, check if the username and password meet certain requirements.

            // Send the signup data to the server for processing.
            boolean signupSuccessful = serverRMI.signup(username, password);

            // You may also want to handle additional error scenarios here,
            // such as if the username is already taken on the server-side.

            return signupSuccessful;
        } catch (RemoteException e)
        {
            System.out.println("Error occurred during signup process.");
            e.printStackTrace();
        }

        return false;
    }
}
