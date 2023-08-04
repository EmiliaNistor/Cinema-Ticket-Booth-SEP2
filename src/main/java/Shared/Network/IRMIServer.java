package Shared.Network;

import Shared.Model.*;

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
     * Returns information about the movie from its id
     * @param movieId The movie's id
     * @return Information about the movie, null if not found
     * @throws RemoteException
     */
    Movie getMovieById(int movieId) throws RemoteException;

    /**
     * Gets all screens in the cinema
     * @return A list of screens
     * @throws RemoteException
     */
    ArrayList<Screen> getAllScreens() throws RemoteException;

    /**
     * Gets a screen by its id
     * @param screenId The screen's id
     * @return Screen with its information, null if not found
     * @throws RemoteException
     */
    Screen getScreenById(int screenId) throws RemoteException;

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
