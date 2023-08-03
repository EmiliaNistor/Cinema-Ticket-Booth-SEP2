package Server.Dbs;

import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Ticket;
import Shared.Model.User;

import java.util.ArrayList;

public interface Database {
     /**
      * Get seat id provided by its details
      * @param screen The screen where the seat is located
      * @param row Seat's row
      * @param number Seat's number
      * @return Seat id
      */
     int getSeatId(int screen, String row, int number);

     /**
      * Make a purchase with the provided ticket's info (ticket id ignored)
      * @param ticket The ticket containing purchase request's info
      * @return The newly created ticket after the purchase, null if failed
      */
     Ticket makePurchase(Ticket ticket);

     /**
      * Get information about a ticket
      * @param ticketId The ticket to fetch
      * @return Ticket containing all of it's information
      */
     Ticket getTicket(int ticketId);

     /**
      * Cancel a ticket
      * @param ticketId The ticket's id to cancel
      */
     void cancelTicket(int ticketId);

     /**
      * Add a new menu option
      * @param food The menu's food
      * @param price The price of the menu
      * @return Newly created menu, null if failed
      */
     Menu addMenu(String food, double price);

     /**
      * Get all available menus
      * @return All menus in the database
      */
     ArrayList<Menu> getMenus();

     /**
      * Get all movies within the cinema
      * @return A list of movies
      */
     ArrayList<Movie> getAllMovies();

     /**
      * Creates a new user account without administrator privileges
      * @param username The username of the account (MUST BE UNIQUE)
      * @param password The password of the account
      * @return The newly created user account, null if failed
      */
     User createAccount(String username, String password);

     /**
      * Checks user's credentials and returns the signed in user
      * @param username The username of the account
      * @param password The password of the account
      * @return The signed in user
      */
     User checkUserCredentials(String username, String password);
}
