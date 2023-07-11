package Models;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Ticket> tickets;
    private List<Screen> screens;
    private List<Menu> menu;

    public Cinema(List<Ticket> tickets, List<Screen> screens, List<Menu> menu) {
        this.tickets = tickets;
        this.screens = screens;
        this.menu = menu;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public void removeScreen(Screen screen) {
        screens.remove(screen);
    }

    public void addMenu(Menu menu) {
        this.menu.add(menu);
    }

    public void removeMenu(Menu menu) {
        this.menu.remove(menu);
    }
}
