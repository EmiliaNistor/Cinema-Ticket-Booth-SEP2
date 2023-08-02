package Shared.Network;

import Shared.Model.Menu;
import Shared.Model.Movie;
import Shared.Model.Ticket;
import Shared.Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IRMIServer extends Remote
{
    /**
     * Gets the information about a ticket provided by its id
     * @param ticketId The ticket id
     * @return Ticket with its information, null if not found
     * @throws RemoteException
     */
    Ticket getTicketById(int ticketId) throws RemoteException;

    /**
     * Gets all movies in the cinema
     * @return A list of movies
     * @throws RemoteException
     */
    ArrayList<Movie> getAllMovies() throws RemoteException;

    /**
     * Allows the user to register a new account
     * @param username The username of the user (MUST BE UNIQUE)
     * @param password The password of the user
     * @return Newly created user account, null if failed
     * @throws RemoteException
     */
    User signUp(String username, String password) throws RemoteException;

    /**
     * Allows to log in to a user account to access more features
     * @param username The username of the account
     * @param password The password of the account
     * @return User account's information, null if failed
     * @throws RemoteException
     */
    User logIn(String username, String password) throws RemoteException;

    /**
     * Gets all menu item options in the cinema
     * @return A list of menu items
     * @throws RemoteException
     */
    ArrayList<Menu> getAllMenuItems() throws RemoteException;

    /**
     * Make a ticket purchase for a movie
     * @param ticket Ticket, containing information about the movie, screen, etc. ticketId is ignored
     * @throws RemoteException
     */
    Ticket purchase(Ticket ticket) throws RemoteException;

    /**
     * Cancel a ticket
     * @param ticketId The ticket's id to cancel
     * @throws RemoteException
     */
    void cancelTicket(int ticketId) throws RemoteException;

    void addMovie(Movie movie) throws RemoteException;

    void updateMovie(Movie movie) throws RemoteException;


    void addMenuItem(Menu menu) throws RemoteException;

    void updateMenuItem(Menu menu) throws RemoteException;

    void deleteMenuItem(Menu menu) throws RemoteException;
}
