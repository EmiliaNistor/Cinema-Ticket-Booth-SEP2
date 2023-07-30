package Server.Dbs;

import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Seat;
import Shared.Model.Ticket;

import java.util.ArrayList;

public interface Database {
     int getSeatId(int screen, String row, int number);
     Ticket makePurchase(Ticket ticket);
     Ticket getTicket(int ticketId);
     void cancelTicket(int ticketId);
     Menu addMenu(String food, double price);
     ArrayList<Menu> getMenus();

}
