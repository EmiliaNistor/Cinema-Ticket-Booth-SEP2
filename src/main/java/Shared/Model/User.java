package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
    private final int userId;
    private final String username;
    private final String password;
    private final boolean administrator;

    public User(int userId, String username, String password, boolean administrator) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
    }

    /**
     * The user's id saved on the server
     * @return User's id
     */
    public int getUserId() {return userId;}

    /**
     * User's username used for login purposes
     * @return User's username
     */
    public String getUsername() {return username;}
    /**
     * User's password used for login purposes
     * @return User's password
     */
    public String getPassword() {return password;}
    /**
     * Whether a user is an administrator
     * @return User's administrator status
     */
    public boolean getAdministrator() {return administrator;}
}

