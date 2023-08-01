package Shared.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
    private String username;
    private String password;
    private ArrayList<Menu> menus;
    private ArrayList<Ticket> tickets;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.menus = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    // Getters and setters

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }
}

