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

    public int getUserId() {return userId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public boolean getAdministrator() {return administrator;}
}

